package nl.nubilus.mollie.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.nubilus.mollie.MollieConfiguration;
import nl.nubilus.mollie.api.error.MollieErrorResponse;
import nl.nubilus.mollie.api.payment.MolliePaymentRequest;
import nl.nubilus.mollie.api.payment.MolliePaymentResponse;
import nl.nubilus.mollie.exception.MollieConnectionException;
import nl.nubilus.mollie.exception.MollieHttpException;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class MollieRestPaymentClient implements MolliePaymentClient {

    private final MollieConfiguration mollieConfiguration;

    public MollieRestPaymentClient(MollieConfiguration mollieConfiguration) {
        this.mollieConfiguration = mollieConfiguration;
        if (this.mollieConfiguration.getMollieUrl() == null || this.mollieConfiguration.getMollieUrl().trim().isEmpty()) {
            this.mollieConfiguration.setMollieUrl("https://api.mollie.com/v2");
        }
    }

    @Override
    public MolliePaymentResponse createPayment(MolliePaymentRequest paymentRequest) throws MollieConnectionException, MollieHttpException {
        try {
            URL url = new URL(this.mollieConfiguration.getMollieUrl() + "/payments");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Accept", "application/hal+json");
            conn.setRequestProperty("Authorization", "Bearer " + mollieConfiguration.getApiKey());
            conn.setDoOutput(true);
            ObjectMapper objectMapper = new ObjectMapper();
            String body = objectMapper.writer().writeValueAsString(paymentRequest);
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = body.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            if (conn.getResponseCode() > 399) {
                MollieErrorResponse exception = objectMapper.readValue(conn.getErrorStream().readAllBytes(), MollieErrorResponse.class);
                if (exception.getField() == null || exception.getField().isEmpty()) {
                    throw new MollieHttpException(conn.getResponseCode(), exception.getTitle(), exception.getDetail());
                } else {
                    throw new MollieHttpException(conn.getResponseCode(), exception.getTitle(), exception.getDetail(), exception.getField());
                }
            }


            return objectMapper.readValue(conn.getInputStream().readAllBytes(), MolliePaymentResponse.class);

        } catch (IOException e) {
            throw new MollieConnectionException(e);
        }
    }
}

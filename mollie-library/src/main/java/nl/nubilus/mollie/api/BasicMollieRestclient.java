package nl.nubilus.mollie.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.nubilus.mollie.MollieConfiguration;
import nl.nubilus.mollie.api.error.MollieErrorResponse;
import nl.nubilus.mollie.exception.MollieConnectionException;
import nl.nubilus.mollie.exception.MollieHttpException;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public abstract class BasicMollieRestclient {

    private final MollieConfiguration mollieConfiguration;
    private final ObjectMapper objectMapper = new ObjectMapper();

    protected BasicMollieRestclient(MollieConfiguration mollieConfiguration) {
        this.mollieConfiguration = mollieConfiguration;
        if (this.mollieConfiguration.getMollieUrl() == null || this.mollieConfiguration.getMollieUrl().trim().isEmpty()) {
            this.mollieConfiguration.setMollieUrl("https://api.mollie.com/v2");
        }
    }

    protected MollieConfiguration mollieConfiguration() {
        return mollieConfiguration;
    }

    protected <T> T post(URL url, Object bodyObject, Class<T> valueType) throws MollieConnectionException, MollieHttpException {
        try {
            HttpURLConnection conn = createConnection(url, "POST");
            String body = objectMapper.writer().writeValueAsString(bodyObject);
            conn.setDoOutput(true);
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = body.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            return response(valueType, conn);
        } catch (IOException e) {
            throw new MollieConnectionException(e);
        }
    }

    private <T> T response(Class<T> valueType, HttpURLConnection conn) throws IOException, MollieHttpException {
        if (conn.getResponseCode() > 399) {
            MollieErrorResponse exception = objectMapper.readValue(conn.getErrorStream().readAllBytes(), MollieErrorResponse.class);
            if (exception.getField() == null || exception.getField().isEmpty()) {
                throw new MollieHttpException(conn.getResponseCode(), exception.getTitle(), exception.getDetail());
            } else {
                throw new MollieHttpException(conn.getResponseCode(), exception.getTitle(), exception.getDetail(), exception.getField());
            }
        }
        return objectMapper.readValue(conn.getInputStream().readAllBytes(), valueType);
    }


    protected <T> T get(URL url, Class<T> valueType) throws MollieConnectionException, MollieHttpException {
        try {
            HttpURLConnection conn = createConnection(url, "GET");
            return response(valueType, conn);
        } catch (IOException e) {
            throw new MollieConnectionException(e);
        }
    }

    private HttpURLConnection createConnection(URL url, String connectionType) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod(connectionType);
        conn.setRequestProperty("Accept", "application/hal+json");
        conn.setRequestProperty("Authorization", "Bearer " + mollieConfiguration().getApiKey());
        return conn;
    }
}

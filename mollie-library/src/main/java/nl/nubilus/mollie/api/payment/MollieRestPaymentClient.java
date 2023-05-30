package nl.nubilus.mollie.api.payment;

import nl.nubilus.mollie.MollieConfiguration;
import nl.nubilus.mollie.api.BasicMollieRestclient;
import nl.nubilus.mollie.exception.MollieConnectionException;
import nl.nubilus.mollie.exception.MollieHttpException;

import java.io.IOException;
import java.net.URL;

public class MollieRestPaymentClient extends BasicMollieRestclient implements MolliePaymentClient {

    public MollieRestPaymentClient(MollieConfiguration mollieConfiguration) {
        super(mollieConfiguration);
    }

    @Override
    public MolliePaymentResponse createPayment(MolliePaymentRequest paymentRequest) throws MollieConnectionException, MollieHttpException {
        try {
            URL url = new URL(this.mollieConfiguration().getMollieUrl() + "/payments");
            return post(url, paymentRequest, MolliePaymentResponse.class);
        } catch (IOException e) {
            throw new MollieConnectionException(e);
        }
    }

    @Override
    public MolliePaymentResponse getPaymentStatus(String paymentId) throws MollieConnectionException, MollieHttpException {
        try {
            URL url = new URL(String.format("%s/payments/%s", this.mollieConfiguration().getMollieUrl(), paymentId));
            return get(url, MolliePaymentResponse.class);
        } catch (IOException e) {
            throw new MollieConnectionException(e);
        }
    }
}

package nl.nubilus.mollie.api.payment;

import nl.nubilus.mollie.exception.MollieConnectionException;
import nl.nubilus.mollie.exception.MollieHttpException;

public interface MolliePaymentClient {
    MolliePaymentResponse createPayment(MolliePaymentRequest paymentRequest) throws MollieConnectionException, MollieHttpException;

    MolliePaymentResponse getPaymentStatus(String paymentId) throws MollieConnectionException, MollieHttpException;
}

package nl.nubilus.mollie.api;

import nl.nubilus.mollie.api.payment.MolliePaymentRequest;
import nl.nubilus.mollie.api.payment.MolliePaymentResponse;
import nl.nubilus.mollie.exception.MollieConnectionException;
import nl.nubilus.mollie.exception.MollieHttpException;

public interface MolliePaymentClient {
    MolliePaymentResponse createPayment(MolliePaymentRequest paymentRequest) throws MollieConnectionException, MollieHttpException;
}

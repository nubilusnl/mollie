package nl.nubilus.mollie.payment;

import nl.nubilus.mollie.MollieConfiguration;
import nl.nubilus.mollie.api.MollieClientFactory;
import nl.nubilus.mollie.api.payment.MollieAmount;
import nl.nubilus.mollie.api.payment.MolliePaymentClient;
import nl.nubilus.mollie.api.payment.MolliePaymentRequest;
import nl.nubilus.mollie.api.payment.MolliePaymentResponse;
import nl.nubilus.mollie.exception.MollieConnectionException;
import nl.nubilus.mollie.exception.MollieHttpException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Currency;

public class MolliePaymentService {

    private final MolliePaymentClient molliePaymentClient;

    public MolliePaymentService(MollieConfiguration mollieConfiguration) {
        this.molliePaymentClient = MollieClientFactory.createMollieClient(mollieConfiguration);
    }

    public PaymentInfo createPayment(Payment payment) throws MollieConnectionException, MollieHttpException {
        MolliePaymentRequest molliePaymentRequest = convertToMolliePaymentRequest(payment);
        MolliePaymentResponse molliePaymentResponse = molliePaymentClient.createPayment(molliePaymentRequest);
        return convertToPaymentInfo(molliePaymentResponse);
    }


    public PaymentInfo getPaymentStatus(String paymentId) throws MollieConnectionException, MollieHttpException {
        return convertToPaymentInfo(molliePaymentClient.getPaymentStatus(paymentId));
    }

    private PaymentInfo convertToPaymentInfo(MolliePaymentResponse molliePaymentResponse) {
        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setPayment(convertToPayment(molliePaymentResponse));
        if (molliePaymentResponse.getLinks() != null && molliePaymentResponse.getLinks().getCheckout() != null) {
            paymentInfo.setCheckoutUrl(molliePaymentResponse.getLinks().getCheckout().getHref());
        }
        paymentInfo.setCreatedAt(LocalDateTime.parse(molliePaymentResponse.getCreatedAt(), DateTimeFormatter.ISO_OFFSET_DATE_TIME));
        paymentInfo.setPaymentId(molliePaymentResponse.getId());
        paymentInfo.setStatus(PaymentStatus.fromValue(molliePaymentResponse.getStatus().stringValue()));
        return paymentInfo;
    }

    private Payment convertToPayment(MolliePaymentResponse molliePaymentResponse) {
        return new Payment(new BigDecimal(molliePaymentResponse.getAmount().getValue()), molliePaymentResponse.getDescription(),
                Currency.getInstance(molliePaymentResponse.getAmount().getCurrency()), molliePaymentResponse.getRedirectUrl());
    }

    private MolliePaymentRequest convertToMolliePaymentRequest(Payment payment) {
        MolliePaymentRequest request = new MolliePaymentRequest();
        request.setRedirectUrl(payment.getRedirectUrl());
        request.setAmount(new MollieAmount(payment.getCurrency().getCurrencyCode(), payment.getAmount().toString()));
        request.setDescription(payment.getDescription());
        return request;
    }

}

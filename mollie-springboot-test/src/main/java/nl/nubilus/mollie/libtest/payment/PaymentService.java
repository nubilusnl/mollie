package nl.nubilus.mollie.libtest.payment;

import nl.nubilus.mollie.MollieConfiguration;
import nl.nubilus.mollie.MollieService;
import nl.nubilus.mollie.exception.MollieConnectionException;
import nl.nubilus.mollie.exception.MollieHttpException;
import nl.nubilus.mollie.payment.Payment;
import nl.nubilus.mollie.payment.PaymentInfo;
import nl.nubilus.mollie.libtest.invoice.Invoice;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import java.util.Currency;


@Service
public class PaymentService {
    private final MollieService mollieService;

    public PaymentService(@Value("${mollie.url}") String url) {
        MollieConfiguration mollieConfiguration = new MollieConfiguration();
        mollieConfiguration.setMollieUrl(url);

        this.mollieService = new MollieService(mollieConfiguration);
    }

    public PaymentInfo createPayment(Invoice invoice) throws MollieConnectionException, MollieHttpException {
        Payment payment = new Payment(invoice.getAmount(), invoice.getDescription());
        payment.setCurrency(Currency.getInstance("EUR"));
        return mollieService.createMolliePaymentService().createPayment(payment);
    }

    public PaymentInfo getPayment(String paymentId) throws MollieConnectionException, MollieHttpException {
        return mollieService.createMolliePaymentService().getPaymentStatus(paymentId);
    }
}

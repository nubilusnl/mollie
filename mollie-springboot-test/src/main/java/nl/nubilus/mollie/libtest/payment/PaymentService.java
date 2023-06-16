package nl.nubilus.mollie.libtest.payment;

import nl.nubilus.mollie.MollieConfiguration;
import nl.nubilus.mollie.MollieService;
import nl.nubilus.mollie.exception.MollieConnectionException;
import nl.nubilus.mollie.exception.MollieHttpException;
import nl.nubilus.mollie.libtest.invoice.Invoice;
import nl.nubilus.mollie.payment.Payment;
import nl.nubilus.mollie.payment.PaymentBuilder;
import nl.nubilus.mollie.payment.PaymentCreationException;
import nl.nubilus.mollie.payment.PaymentInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class PaymentService {
    private final MollieService mollieService;

    public PaymentService(@Value("${mollie.url}") String url) {
        MollieConfiguration mollieConfiguration = new MollieConfiguration();
        mollieConfiguration.setMollieUrl(url);

        this.mollieService = new MollieService(mollieConfiguration);
    }

    public PaymentInfo createPayment(Invoice invoice) throws MollieConnectionException, MollieHttpException, PaymentCreationException {
        Payment payment = new PaymentBuilder().setAmount(invoice.getAmount()).setDescription(invoice.getDescription())
                .setRedirectUrl("http://localhost/redirect").createPayment();
        return mollieService.createMolliePaymentService().createPayment(payment);
    }

    public PaymentInfo getPayment(String paymentId) throws MollieConnectionException, MollieHttpException {
        return mollieService.createMolliePaymentService().getPaymentStatus(paymentId);
    }
}

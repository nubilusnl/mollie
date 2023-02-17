package nl.nubilus.mollie.libtest.invoice;

import nl.nubilus.mollie.exception.MollieConnectionException;
import nl.nubilus.mollie.exception.MollieHttpException;
import nl.nubilus.mollie.libtest.payment.PaymentService;
import nl.nubilus.mollie.payment.PaymentInfo;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/payment")
public class InvoiceRestEndpoint {

    private final PaymentService paymentService;

    public InvoiceRestEndpoint(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public PaymentInfo createPayment(@RequestBody Invoice invoice) throws MollieConnectionException, MollieHttpException {
        return paymentService.createPayment(invoice);
    }
}

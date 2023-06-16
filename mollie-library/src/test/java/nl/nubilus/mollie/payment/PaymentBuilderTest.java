package nl.nubilus.mollie.payment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PaymentBuilderTest {

    private PaymentBuilder paymentBuilder;

    @BeforeEach
    void setUp() {
        paymentBuilder = new PaymentBuilder();
    }

    @Test
    void createPayment() throws PaymentCreationException {
        Payment result = paymentBuilder.setCurrency(Currency.getInstance("USD"))
                .setDescription("omschrijving")
                .setAmount(BigDecimal.valueOf(12.5)).setRedirectUrl("http://localhost")
                .createPayment();
        assertThat(result.getAmount()).isEqualTo(BigDecimal.valueOf(12.5).setScale(2, RoundingMode.HALF_UP));
        assertThat(result.getDescription()).isEqualTo("omschrijving");
        assertThat(result.getCurrency()).isEqualTo(Currency.getInstance("USD"));
        assertThat(result.getRedirectUrl()).isEqualTo("http://localhost");
    }

    @Test
    void createPaymentDefaultCurrency() throws PaymentCreationException {
        Payment result = paymentBuilder
                .setDescription("omschrijving")
                .setAmount(BigDecimal.valueOf(12.5)).setRedirectUrl("http://localhost")
                .createPayment();
        assertThat(result.getAmount()).isEqualTo(BigDecimal.valueOf(12.5).setScale(2, RoundingMode.HALF_UP));
        assertThat(result.getDescription()).isEqualTo("omschrijving");
        assertThat(result.getCurrency()).isEqualTo(Currency.getInstance("EUR"));
        assertThat(result.getRedirectUrl()).isEqualTo("http://localhost");
    }

    @Test
    void createPaymentMissingDescription() {
        PaymentCreationException exception = assertThrows(PaymentCreationException.class, () ->  paymentBuilder
                .setAmount(BigDecimal.valueOf(12.5)).setRedirectUrl("http://localhost")
                .createPayment());
        assertThat(exception.getMessage()).isEqualTo("One of the required fields is null: amount: 12.5, description: null or redirectUrl: http://localhost");
    }
    @Test
    void createPaymentMissingRedirectUrl() {
        PaymentCreationException exception = assertThrows(PaymentCreationException.class, () ->  paymentBuilder
                .setDescription("omschrijving")
                .setAmount(BigDecimal.valueOf(12.5))
                .createPayment());
        assertThat(exception.getMessage()).isEqualTo("One of the required fields is null: amount: 12.5, description: omschrijving or redirectUrl: null");
    }

    @Test
    void createPaymentMissingAmount() {
        PaymentCreationException exception = assertThrows(PaymentCreationException.class, () ->  paymentBuilder
                .setDescription("omschrijving").setRedirectUrl("http://localhost")
                .createPayment());
        assertThat(exception.getMessage()).isEqualTo("One of the required fields is null: amount: null, description: omschrijving or redirectUrl: http://localhost");
    }
}

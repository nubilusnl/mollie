package nl.nubilus.mollie.payment;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

public class PaymentBuilder {
    private BigDecimal amount;
    private String description;
    private Currency currency;
    private String redirectUrl;

    private List<PaymentMethod> methods;
    private String cancelUrl;
    private String webhookUrl;
    private Locale locale;
    private Locale restrictPaymentMethodsToCountry;
    private Object metadata;
    private String customerId;

    public PaymentBuilder setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public PaymentBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public PaymentBuilder setCurrency(Currency currency) {
        this.currency = currency;
        return this;
    }

    public PaymentBuilder setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
        return this;
    }

    public PaymentBuilder setMethods(List<PaymentMethod> methods) {
        this.methods = methods;
        return this;
    }

    public PaymentBuilder setCancelUrl(String cancelUrl) {
        this.cancelUrl = cancelUrl;
        return this;
    }

    public PaymentBuilder setWebhookUrl(String webhookUrl) {
        this.webhookUrl = webhookUrl;
        return this;
    }

    public PaymentBuilder setLocale(Locale locale) {
        this.locale = locale;
        return this;
    }

    public PaymentBuilder setRestrictPaymentMethodsToCountry(Locale restrictPaymentMethodsToCountry) {
        this.restrictPaymentMethodsToCountry = restrictPaymentMethodsToCountry;
        return this;
    }

    public PaymentBuilder setMetadata(Object metadata) {
        this.metadata = metadata;
        return this;
    }

    public PaymentBuilder setCustomerId(String customerId) {
        this.customerId = customerId;
        return this;
    }

    /**
     * Create a payment, requires amount, description and redirectUrl. If there is no currency, we will default to EUR
     * @return the created payment
     * @exception PaymentCreationException if one of the required fields (amount, description or redirectUrl) is not filled
     */
    public Payment createPayment() throws PaymentCreationException {
        if (amount == null || description == null || redirectUrl == null) {
            throw new PaymentCreationException(String.format("One of the required fields is null: amount: %s, description: %s or redirectUrl: %s", amount, description, redirectUrl));
        }
        if (currency == null) {
            currency = Currency.getInstance("EUR");
        }
        Payment payment = new Payment(amount, description, currency, redirectUrl);
        payment.setMethods(this.methods);
        payment.setLocale(this.locale);
        payment.setMetadata(this.metadata);
        payment.setCancelUrl(this.cancelUrl);
        payment.setCustomerId(this.customerId);
        payment.setWebhookUrl(this.webhookUrl);
        payment.setRestrictPaymentMethodsToCountry(this.restrictPaymentMethodsToCountry);
        return payment;
    }

}

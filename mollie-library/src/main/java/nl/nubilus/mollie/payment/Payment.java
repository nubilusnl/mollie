package nl.nubilus.mollie.payment;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

public class Payment {

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


    public void setMethods(List<PaymentMethod> methods) {
        this.methods = methods;
    }

    public void setCancelUrl(String cancelUrl) {
        this.cancelUrl = cancelUrl;
    }

    public void setWebhookUrl(String webhookUrl) {
        this.webhookUrl = webhookUrl;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public void setRestrictPaymentMethodsToCountry(Locale restrictPaymentMethodsToCountry) {
        this.restrictPaymentMethodsToCountry = restrictPaymentMethodsToCountry;
    }

    public void setMetadata(Object metadata) {
        this.metadata = metadata;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Payment(BigDecimal amount, String description, Currency currency, String redirectUrl) {
        this.setAmount(amount);
        this.setDescription(description);
        this.setCurrency(currency);
        this.setRedirectUrl(redirectUrl);
    }

    public BigDecimal getAmount() {
        return amount;
    }

    private void setAmount(BigDecimal amount) {
        if (amount != null) {
            this.amount = amount.setScale(2, RoundingMode.HALF_UP);
        }
    }

    public String getDescription() {
        return description;
    }

    private void setDescription(String description) {
        this.description = description;
    }

    public Currency getCurrency() {
        return currency;
    }

    private void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    private void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }
}

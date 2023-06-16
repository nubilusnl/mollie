package nl.nubilus.mollie.payment;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;

public class Payment {

    private BigDecimal amount;

    private String description;
    private Currency currency;

    private String redirectUrl;

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

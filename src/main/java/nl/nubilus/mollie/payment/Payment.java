package nl.nubilus.mollie.payment;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;

public class Payment {

    private BigDecimal amount;

    private String description;
    private Currency currency;

    private String redirectUrl;

    public Payment(BigDecimal amount, String description) {
        this.setAmount(amount);
        this.setDescription(description);
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        if (amount != null) {
            this.amount = amount.setScale(2, RoundingMode.HALF_UP);
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }
}

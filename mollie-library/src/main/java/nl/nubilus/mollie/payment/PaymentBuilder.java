package nl.nubilus.mollie.payment;

import java.math.BigDecimal;
import java.util.Currency;

public class PaymentBuilder {
    private BigDecimal amount;
    private String description;
    private Currency currency;
    private String redirectUrl;

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
        return new Payment(amount, description, currency, redirectUrl);
    }

}

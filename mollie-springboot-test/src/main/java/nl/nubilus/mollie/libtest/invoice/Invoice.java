package nl.nubilus.mollie.libtest.invoice;

import java.math.BigDecimal;

public class Invoice {

    private BigDecimal amount = BigDecimal.ZERO;

    private String description;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

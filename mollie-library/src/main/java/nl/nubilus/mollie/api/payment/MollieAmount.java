package nl.nubilus.mollie.api.payment;

public class MollieAmount {
    private String currency;
    private String value;

    public MollieAmount(String currency, String value) {
        this.currency = currency;
        this.value = value;
    }

    public MollieAmount() {
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

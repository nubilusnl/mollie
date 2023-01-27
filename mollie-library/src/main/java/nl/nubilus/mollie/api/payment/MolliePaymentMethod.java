package nl.nubilus.mollie.api.payment;


import com.fasterxml.jackson.annotation.JsonValue;

public enum MolliePaymentMethod {
    APPLEPAY("applepay"),
    BANCONTACT("bancontact"),
    BANKTRANSFER("banktransfer"),
    BELFIUS("belfius "),
    CREDITCARD("creditcard"),
    DIRECTDEBIT("directdebit"),
    EPS("eps"),
    GIFTCARD("giftcard"),
    GIROPAY("giropay"),
    IDEAL("ideal"),
    KBC("kbc"),
    MYBANK("mybank"),
    PAYPAL("paypal"),
    PAYSAFECARD("paysafecard"),
    PRZELEWY24("przelewy24"),
    SOFORT("sofort");

    private final String method;

    MolliePaymentMethod(String method) {
        this.method = method;
    }

    @JsonValue
    public String stringValue() {
        return method;
    }
}

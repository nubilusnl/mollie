package nl.nubilus.mollie.payment;

public enum PaymentMethod {
    APPLEPAY("applepay"),
    BANCONTACT("bancontact"),
    BANKTRANSFER("banktransfer"),
    BELFIUS("belfius"),
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

    PaymentMethod(String method) {
        this.method = method;
    }

    public String stringValue() {
        return method;
    }
}

package nl.nubilus.mollie.payment;

public enum PaymentStatus {
    OPEN("open"),
    CANCELED("canceled"),
    PENDING("pending"),
    AUTHORIZED("authorized"),
    EXPIRED("expired"),
    FAILED("failed"),
    PAID("paid");
    private final String status;

    PaymentStatus(String status) {
        this.status = status;
    }

    public String stringValue() {
        return status;
    }

    public static PaymentStatus fromValue(String givenName) {
        for (PaymentStatus paymentStatus : values()) {
            if (paymentStatus.stringValue().equals(givenName)) {
                return paymentStatus;
            }
        }
        return null;
    }
}

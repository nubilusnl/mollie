package nl.nubilus.mollie.api.payment;

import com.fasterxml.jackson.annotation.JsonValue;

public enum MolliePaymentStatus {
    OPEN("open"),
    CANCELED("canceled"),
    PENDING("pending"),
    AUTHORIZED("authorized"),
    EXPIRED("expired"),
    FAILED("failed"),
    PAID("paid");

    private final String status;

    MolliePaymentStatus(String status) {
        this.status = status;
    }

    @JsonValue
    public String stringValue() {
        return status;
    }
}

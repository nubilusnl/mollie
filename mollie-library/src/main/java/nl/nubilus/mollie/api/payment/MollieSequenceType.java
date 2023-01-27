package nl.nubilus.mollie.api.payment;

import com.fasterxml.jackson.annotation.JsonValue;

public enum MollieSequenceType {
    ONEOFF("oneoff"),
    FIRST("first"),
    RECURRING("recurring");

    private final String type;

    MollieSequenceType(String type) {
        this.type = type;
    }

    @JsonValue
    public String stringValue() {
        return type;
    }
}

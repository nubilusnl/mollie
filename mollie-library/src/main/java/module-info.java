module nl.nubilus.mollie {
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.core;
    exports nl.nubilus.mollie;
    exports nl.nubilus.mollie.payment;
    exports nl.nubilus.mollie.exception;
    exports nl.nubilus.mollie.api.payment to com.fasterxml.jackson.databind;
    exports nl.nubilus.mollie.api.links to com.fasterxml.jackson.databind;
    exports nl.nubilus.mollie.api.error to com.fasterxml.jackson.databind;
}

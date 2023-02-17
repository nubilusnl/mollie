module mollie.springboot.test {
    requires nl.nubilus.mollie;
    requires spring.web;
    requires spring.context;
    requires spring.boot.autoconfigure;
    requires spring.boot;
    requires spring.beans;

    exports nl.nubilus.mollie.libtest to spring.context, spring.beans;
    exports nl.nubilus.mollie.libtest.payment to spring.beans;
    exports nl.nubilus.mollie.libtest.invoice to spring.beans, com.fasterxml.jackson.databind, spring.web;
}

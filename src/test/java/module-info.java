open module mollie
{
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.core;
    exports nl.nubilus.mollie;
    exports nl.nubilus.mollie.payment;
    exports nl.nubilus.mollie.api;

    requires org.mockito;
    requires org.apache.commons.io;
    requires org.mockito.junit.jupiter;
    requires net.bytebuddy.agent;
    requires net.bytebuddy;
    requires org.assertj.core;
    requires transitive org.junit.jupiter.engine;
    requires transitive org.junit.jupiter.api;
    requires wiremock.jre8.standalone;

}

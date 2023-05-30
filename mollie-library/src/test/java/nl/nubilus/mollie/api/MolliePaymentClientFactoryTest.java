package nl.nubilus.mollie.api;

import nl.nubilus.mollie.MollieConfiguration;
import nl.nubilus.mollie.api.payment.MolliePaymentClient;
import nl.nubilus.mollie.api.payment.MollieRestPaymentClient;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MolliePaymentClientFactoryTest {

    @Test
    void createMollieClient() {

        MolliePaymentClient molliePaymentClient = MollieClientFactory.createMollieClient(new MollieConfiguration());
        assertThat(molliePaymentClient).isOfAnyClassIn(MollieRestPaymentClient.class);
    }
}

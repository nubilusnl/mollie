package nl.nubilus.mollie.api;

import nl.nubilus.mollie.MollieConfiguration;
import nl.nubilus.mollie.api.payment.MolliePaymentClient;
import nl.nubilus.mollie.api.payment.MollieRestPaymentClient;

public class MollieClientFactory {


    private MollieClientFactory() {
    }

    public static MolliePaymentClient createMollieClient(MollieConfiguration mollieConfiguration) {
        return new MollieRestPaymentClient(mollieConfiguration);
    }
}

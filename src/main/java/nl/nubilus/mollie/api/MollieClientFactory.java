package nl.nubilus.mollie.api;

import nl.nubilus.mollie.MollieConfiguration;

public class MollieClientFactory {


    private MollieClientFactory() {
    }

    public static MolliePaymentClient createMollieClient(MollieConfiguration mollieConfiguration) {
        return new MollieRestPaymentClient(mollieConfiguration);
    }
}

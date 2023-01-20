package nl.nubilus.mollie;

import nl.nubilus.mollie.payment.MolliePaymentService;

public class MollieService {

    private final MollieConfiguration mollieConfiguration;

    public MollieService(MollieConfiguration mollieConfiguration) {
        this.mollieConfiguration = mollieConfiguration;

    }

    public MolliePaymentService createMolliePaymentService() {
        return new MolliePaymentService(mollieConfiguration);
    }

    public MollieConfiguration getMollieConfiguration() {
        return mollieConfiguration;
    }
}

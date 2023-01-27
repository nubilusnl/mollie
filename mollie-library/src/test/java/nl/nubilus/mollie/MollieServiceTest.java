package nl.nubilus.mollie;

import nl.nubilus.mollie.payment.MolliePaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MollieServiceTest {

    private MollieService mollieService;

    private final MollieConfiguration mollieConfiguration = new MollieConfiguration();


    @BeforeEach
    void setUp() {
        mollieService = new MollieService(mollieConfiguration);
    }

    @Test
    void createMolliePaymentService() {
        MolliePaymentService molliePaymentService = mollieService.createMolliePaymentService();
        assertThat(molliePaymentService).isNotNull();
    }

    @Test
    void checkMollieConfiguration() {
        assertThat(mollieService.getMollieConfiguration()).isEqualTo(mollieConfiguration);
        mollieConfiguration.setMollieUrl("https://mollieurl");
        assertThat(mollieService.getMollieConfiguration().getMollieUrl()).isEqualTo("https://mollieurl");
    }

}

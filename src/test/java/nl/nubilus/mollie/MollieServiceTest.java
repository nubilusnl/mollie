package nl.nubilus.mollie;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MollieServiceTest {

    private MollieService mollieService;
    @BeforeEach
    void setUp() {
        mollieService = new MollieService();
    }

    @Test
    void simpleTest() {
        assertEquals("test", mollieService.test());
    }
}

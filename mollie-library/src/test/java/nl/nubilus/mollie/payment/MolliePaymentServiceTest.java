package nl.nubilus.mollie.payment;

import nl.nubilus.mollie.MollieConfiguration;
import nl.nubilus.mollie.api.MollieClientFactory;
import nl.nubilus.mollie.api.links.MollieLink;
import nl.nubilus.mollie.api.links.MollieLinks;
import nl.nubilus.mollie.api.payment.*;
import nl.nubilus.mollie.exception.MollieConnectionException;
import nl.nubilus.mollie.exception.MollieHttpException;
import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MolliePaymentServiceTest {

    private MolliePaymentService molliePaymentService;

    private final MollieConfiguration mollieConfiguration = new MollieConfiguration();
    @Mock
    private MolliePaymentClient molliePaymentClient;

    @BeforeEach
    void setUp() {
        try (MockedStatic<MollieClientFactory> factory = mockStatic(MollieClientFactory.class)) {
            factory.when(() -> MollieClientFactory.createMollieClient(any())).thenReturn(molliePaymentClient);
            mollieConfiguration.setApiKey("apiKey");
            molliePaymentService = new MolliePaymentService(mollieConfiguration);
        }
    }

    @Test
    void simplePayment() throws MollieConnectionException, MollieHttpException, PaymentCreationException {
        Payment payment = new PaymentBuilder().setAmount(BigDecimal.valueOf(12.4)).setDescription("new payment").setRedirectUrl("http://localhost").createPayment();
        MolliePaymentResponse molliePaymentResponse = new MolliePaymentResponse();
        molliePaymentResponse.setCreatedAt("2023-01-13T07:53:22+00:00");
        molliePaymentResponse.setLinks(new MollieLinks());
        molliePaymentResponse.getLinks().setCheckout(new MollieLink());
        molliePaymentResponse.getLinks().getCheckout().setHref("https://www.mollie.com/checkout/select-method/4WgLaGrDTo");
        molliePaymentResponse.setId("tr_4WgLaGrDTo");
        molliePaymentResponse.setAmount(new MollieAmount("EUR", "12.40"));
        molliePaymentResponse.setStatus(MolliePaymentStatus.OPEN);
        molliePaymentResponse.setDescription("new payment");
        ArgumentCaptor<MolliePaymentRequest> molliePaymentRequestArgumentCaptor = ArgumentCaptor.forClass(MolliePaymentRequest.class);

        when(molliePaymentClient.createPayment(molliePaymentRequestArgumentCaptor.capture())).thenReturn(molliePaymentResponse);
        PaymentInfo paymentInfo = molliePaymentService.createPayment(payment);
        assertThat(paymentInfo.getPayment().getDescription()).isEqualTo("new payment");
        assertThat(paymentInfo.getPayment().getAmount()).isCloseTo(BigDecimal.valueOf(12.4), Percentage.withPercentage(0));
        LocalDateTime expectedAt = LocalDateTime.of(2023, 1, 13, 7, 53, 22);
        assertThat(paymentInfo.getCreatedAt()).isCloseTo(expectedAt, within(1, ChronoUnit.SECONDS));
        assertThat(paymentInfo.getCheckoutUrl()).isEqualTo("https://www.mollie.com/checkout/select-method/4WgLaGrDTo");
        assertThat(paymentInfo.getPaymentId()).isEqualTo("tr_4WgLaGrDTo");
        assertThat(paymentInfo.getStatus()).isEqualTo(PaymentStatus.OPEN);
        MolliePaymentRequest request = molliePaymentRequestArgumentCaptor.getValue();
        assertThat(request.getAmount().getCurrency()).isEqualTo("EUR");
        assertThat(request.getAmount().getValue()).isEqualTo("12.40");
        assertThat(request.getRedirectUrl()).isEqualTo("http://localhost");
        assertThat(request.getDescription()).isEqualTo("new payment");
    }

    @Test
    void getPaymentStatus() throws MollieConnectionException, MollieHttpException {
        MolliePaymentResponse molliePaymentResponse = new MolliePaymentResponse();
        molliePaymentResponse.setAmount(new MollieAmount("EUR", "12.40"));
        molliePaymentResponse.setStatus(MolliePaymentStatus.PAID);
        molliePaymentResponse.setCreatedAt("2023-01-13T07:53:22+00:00");
        when(molliePaymentClient.getPaymentStatus("tr_4WgLaGrDTo")).thenReturn(molliePaymentResponse);
        PaymentInfo paymentInfo = molliePaymentService.getPaymentStatus("tr_4WgLaGrDTo");
        assertThat(paymentInfo.getStatus()).isEqualTo(PaymentStatus.PAID);
    }
}

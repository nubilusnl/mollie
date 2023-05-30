package nl.nubilus.mollie.api.payment;

import com.fasterxml.jackson.core.JsonParseException;
import com.github.tomakehurst.wiremock.http.Fault;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import nl.nubilus.mollie.MollieConfiguration;
import nl.nubilus.mollie.exception.MollieConnectionException;
import nl.nubilus.mollie.exception.MollieHttpException;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class MollieRestPaymentClientTest {

    private MolliePaymentClient molliePaymentClient;

    private MollieConfiguration mollieConfiguration;

    @RegisterExtension
    private final static WireMockExtension wiremock = WireMockExtension.newInstance()
            .options(wireMockConfig().dynamicPort())
            .configureStaticDsl(true)
            .build();

    @BeforeEach
    void setUp() {
        mollieConfiguration = new MollieConfiguration();
        mollieConfiguration.setApiKey("apikey");
        mollieConfiguration.setMollieUrl(wiremock.baseUrl());
        molliePaymentClient = new MollieRestPaymentClient(mollieConfiguration);
    }

    @Test
    void createPayment() throws MollieConnectionException, MollieHttpException {
        stubFor(post("/payments").willReturn(aResponse().withStatus(201).withBody(readFile("nl/nubilus/mollie/api/newpaymentresponse.json"))
                        .withHeader("content-type", "application/hal+json"))
                .withRequestBody(equalToJson(readFile("nl/nubilus/mollie/api/newpaymentrequest.json"))).withHeader("Authorization", equalTo("Bearer apikey")));
        MolliePaymentRequest molliePaymentRequest = new MolliePaymentRequest();
        molliePaymentRequest.setDescription("Payment for invoice: invoiceid1245");
        molliePaymentRequest.setAmount(new MollieAmount());
        molliePaymentRequest.getAmount().setCurrency("EUR");
        molliePaymentRequest.getAmount().setValue("24.20");
        molliePaymentRequest.setRedirectUrl("https://webshop.example.org/order/12345/");

        MolliePaymentResponse paymentResponse = molliePaymentClient.createPayment(molliePaymentRequest);

        assertThat(paymentResponse.getDescription()).isEqualTo("Payment for invoice: invoiceid1245");
        assertThat(paymentResponse.getRedirectUrl()).isEqualTo("https://webshop.example.org/order/12345/");
        assertThat(paymentResponse.getId()).isEqualTo("tr_KHjxr6zD6a");
        assertThat(paymentResponse.getAmount().getValue()).isEqualTo("24.20");
        assertThat(paymentResponse.getAmount().getCurrency()).isEqualTo("EUR");
        assertThat(paymentResponse.getStatus()).isEqualTo(MolliePaymentStatus.OPEN);
    }

    @Test
    void createPaymentwithNotAuthorized() {
        stubFor(post("/payments").willReturn(aResponse().withStatus(401).withBody(readFile("nl/nubilus/mollie/api/401response.json"))
                        .withHeader("content-type", "application/hal+json"))
                .withRequestBody(equalToJson(readFile("nl/nubilus/mollie/api/newpaymentrequest.json"))).withHeader("Authorization", equalTo("Bearer apikey")));
        MolliePaymentRequest molliePaymentRequest = new MolliePaymentRequest();
        molliePaymentRequest.setDescription("Payment for invoice: invoiceid1245");
        molliePaymentRequest.setAmount(new MollieAmount());
        molliePaymentRequest.getAmount().setCurrency("EUR");
        molliePaymentRequest.getAmount().setValue("24.20");
        molliePaymentRequest.setRedirectUrl("https://webshop.example.org/order/12345/");

        MollieHttpException exception = assertThrows(MollieHttpException.class, () -> molliePaymentClient.createPayment(molliePaymentRequest));

        assertThat(exception.getDetail()).isEqualTo("Missing authentication, or failed to authenticate");
        assertThat(exception.getTitle()).isEqualTo("Unauthorized Request");
        assertThat(exception.getResponseCode()).isEqualTo(401);
    }

    @Test
    void createPaymentwithUnprocessableEntity() {
        stubFor(post("/payments").willReturn(aResponse().withStatus(422).withBody(readFile("nl/nubilus/mollie/api/422response.json"))
                        .withHeader("content-type", "application/hal+json"))
                .withRequestBody(equalToJson(readFile("nl/nubilus/mollie/api/newpaymentrequest.json"))).withHeader("Authorization", equalTo("Bearer apikey")));
        MolliePaymentRequest molliePaymentRequest = new MolliePaymentRequest();
        molliePaymentRequest.setDescription("Payment for invoice: invoiceid1245");
        molliePaymentRequest.setAmount(new MollieAmount());
        molliePaymentRequest.getAmount().setCurrency("EUR");
        molliePaymentRequest.getAmount().setValue("24.20");
        molliePaymentRequest.setRedirectUrl("https://webshop.example.org/order/12345/");

        MollieHttpException exception = assertThrows(MollieHttpException.class, () -> molliePaymentClient.createPayment(molliePaymentRequest));

        assertThat(exception.getDetail()).isEqualTo("The amount is higher than the maximum");
        assertThat(exception.getTitle()).isEqualTo("Unprocessable Entity");
        assertThat(exception.getResponseCode()).isEqualTo(422);
        assertThat(exception.getField()).isEqualTo("amount");
    }

    @Test
    void createPaymentwithBadJsonBody() {
        stubFor(post("/payments").willReturn(aResponse().withStatus(422).withBody(readFile("nl/nubilus/mollie/api/422badjsonresponse.json"))
                        .withHeader("content-type", "application/hal+json"))
                .withRequestBody(equalToJson(readFile("nl/nubilus/mollie/api/newpaymentrequest.json"))).withHeader("Authorization", equalTo("Bearer apikey")));
        MolliePaymentRequest molliePaymentRequest = new MolliePaymentRequest();
        molliePaymentRequest.setDescription("Payment for invoice: invoiceid1245");
        molliePaymentRequest.setAmount(new MollieAmount());
        molliePaymentRequest.getAmount().setCurrency("EUR");
        molliePaymentRequest.getAmount().setValue("24.20");
        molliePaymentRequest.setRedirectUrl("https://webshop.example.org/order/12345/");

        MollieConnectionException exception = assertThrows(MollieConnectionException.class, () -> molliePaymentClient.createPayment(molliePaymentRequest));

        assertThat(exception.getCause()).isInstanceOf(JsonParseException.class);
    }

    @Test
    void getPaymentWithId() throws MollieConnectionException, MollieHttpException {
        stubFor(get("/payments/tr_KHjxr6zD6a").willReturn(aResponse().withStatus(200).withBody(readFile("nl/nubilus/mollie/api/payment/paidpayment.json"))
                .withHeader("content-type", "application/hal+json")).withHeader("Authorization", equalTo("Bearer apikey")));
        MolliePaymentResponse paymentResponse = molliePaymentClient.getPaymentStatus("tr_KHjxr6zD6a");

        assertThat(paymentResponse.getDescription()).isEqualTo("Payment for invoice: invoiceid1245");
        assertThat(paymentResponse.getRedirectUrl()).isEqualTo("https://webshop.example.org/order/12345/");
        assertThat(paymentResponse.getId()).isEqualTo("tr_KHjxr6zD6a");
        assertThat(paymentResponse.getAmount().getValue()).isEqualTo("24.20");
        assertThat(paymentResponse.getAmount().getCurrency()).isEqualTo("EUR");
        assertThat(paymentResponse.getStatus()).isEqualTo(MolliePaymentStatus.PAID);
    }

    @Test
    void getPaymentWithIdConnectionError() {
        stubFor(get("/payments/tr_KHjxr6zD6a").willReturn(aResponse().withFault(Fault.RANDOM_DATA_THEN_CLOSE)));
        assertThatThrownBy(() -> molliePaymentClient.getPaymentStatus("tr_KHjxr6zD6a")).isInstanceOf(MollieConnectionException.class);
    }

    @Test
    void getPaymentWithIdInvalidURL() {
        mollieConfiguration.setMollieUrl("blabla");
        assertThatThrownBy(() -> molliePaymentClient.getPaymentStatus("tr_KHjxr6zD6a")).isInstanceOf(MollieConnectionException.class);
    }

    @Test
    void createPaymentConnectionError() {
        stubFor(post("/payments").willReturn(aResponse().withFault(Fault.CONNECTION_RESET_BY_PEER)));
        MolliePaymentRequest molliePaymentRequest = new MolliePaymentRequest();
        molliePaymentRequest.setDescription("Payment for invoice: invoiceid1245");
        molliePaymentRequest.setAmount(new MollieAmount());
        molliePaymentRequest.getAmount().setCurrency("EUR");
        molliePaymentRequest.getAmount().setValue("24.20");
        molliePaymentRequest.setRedirectUrl("https://webshop.example.org/order/12345/");
        assertThatThrownBy(() -> molliePaymentClient.createPayment(molliePaymentRequest)).isInstanceOf(MollieConnectionException.class);
    }

    @Test
    void createPaymentInvalidUrl() {
        mollieConfiguration.setMollieUrl("woit");
        MolliePaymentRequest molliePaymentRequest = new MolliePaymentRequest();
        molliePaymentRequest.setDescription("Payment for invoice: invoiceid1245");
        molliePaymentRequest.setAmount(new MollieAmount());
        molliePaymentRequest.getAmount().setCurrency("EUR");
        molliePaymentRequest.getAmount().setValue("24.20");
        molliePaymentRequest.setRedirectUrl("https://webshop.example.org/order/12345/");
        assertThatThrownBy(() -> molliePaymentClient.createPayment(molliePaymentRequest)).isInstanceOf(MollieConnectionException.class);
    }

    public String readFile(String filePath) {
        ClassLoader classLoader = getClass().getClassLoader();
        String data;
        // this because windows will encode urls and use %20 for spaces
        URL url = classLoader.getResource(filePath);
        URI uri;
        try {
            assert url != null;
            uri = new URI(url.toString());
            File file = new File(uri.getPath());
            data = FileUtils.readFileToString(file, StandardCharsets.UTF_8.name());
            return data;
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }

    }
}

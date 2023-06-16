package nl.nubilus.mollie.libtest;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import wiremock.org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PaymentStepdefs {

    private ResponseEntity<String> response;
    private final TestRestTemplate testRestTemplate;

    public PaymentStepdefs(TestRestTemplate testRestTemplate) {
        this.testRestTemplate = testRestTemplate;
    }

    @When("invoice {string} is used to send payment")
    public void invoiceInvoiceJsonIsUsedToSendPayment(String fileName) {
        stubFor(post("/mollie/payments").willReturn(okJson(readFile("testdata/payment/newpaymentresponse.json"))));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(readFile(String.format("testdata/payment/%s", fileName)), headers);
        response = testRestTemplate.postForEntity("/rest/payment", request, String.class);
    }

    @Then("the response should be {string}")
    public void theResponseShouldBeNewpaymentresponseJson(String fileName) throws JSONException {
        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        String expected = readFile(String.format("testdata/payment/%s", fileName));
        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    public String readFile(String filePath) {
        ClassLoader classLoader = getClass().getClassLoader();
        String data;
        // this because windows will encode urls and use %20 for spaces
        URL url = classLoader.getResource(filePath);
        URI uri;
        try {
            uri = new URI(url.toString());
            File file = new File(uri.getPath());
            data = FileUtils.readFileToString(file, StandardCharsets.UTF_8.name());
            return data;
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }

    }


    @When("the payment status for payment {string} is being retrieved")
    public void thePaymentStatusForPaymentTr_WDqYKVllgIsBeingRetrieved(String paymentId) {
        stubFor(get(String.format("/mollie/payments/%s", paymentId)).willReturn(okJson(readFile("testdata/payment/statuspaymentresponse.json"))));
        response = testRestTemplate.getForEntity(String.format("/rest/payment/%s", paymentId), String.class);
    }
}

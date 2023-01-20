package nl.nubilus.mollie;

public class MollieConfiguration {

    private String apiKey;

    private String mollieUrl;

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getMollieUrl() {
        return mollieUrl;
    }

    public void setMollieUrl(String mollieUrl) {
        this.mollieUrl = mollieUrl;
    }
}

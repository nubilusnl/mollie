package nl.nubilus.mollie.exception;

public class MollieHttpException extends Exception {

    private final int responseCode;

    private final String title;

    private final String detail;

    private final String field;

    public MollieHttpException(int responseCode, String title, String detail) {
        super(String.format("Mollie responded with: Response code %d, Title: %s, detail: %s", responseCode, title, detail));
        this.field = null;
        this.responseCode = responseCode;
        this.title = title;
        this.detail = detail;
    }

    public MollieHttpException(int responseCode, String title, String detail, String field) {
        super(String.format("Mollie responded with: Response code %d, Title: %s, detail: %s, field: %s", responseCode, title, detail, field));
        this.responseCode = responseCode;
        this.title = title;
        this.detail = detail;
        this.field = field;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public String getTitle() {
        return title;
    }

    public String getDetail() {
        return detail;
    }

    public String getField() {
        return field;
    }
}

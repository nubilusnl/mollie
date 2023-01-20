package nl.nubilus.mollie.api.error;

import com.fasterxml.jackson.annotation.JsonProperty;
import nl.nubilus.mollie.api.links.MollieLinks;

public class MollieErrorResponse {
    private String status;
    private String title;
    private String detail;
    private String field;
    @JsonProperty("_links")
    private MollieLinks links;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public MollieLinks getLinks() {
        return links;
    }

    public void setLinks(MollieLinks links) {
        this.links = links;
    }
}

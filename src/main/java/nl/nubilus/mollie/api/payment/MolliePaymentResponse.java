package nl.nubilus.mollie.api.payment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import nl.nubilus.mollie.api.links.MollieLinks;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MolliePaymentResponse {
    private String resource;
    private String id;
    private String mode;
    private String createdAt;
    private MollieAmount amount;
    private String description;
    private String method;
    private Object metadata;
    private MolliePaymentStatus status;
    private Boolean isCancelable;
    private String expiresAt;
    private String profileId;
    private String customerId;
    private MollieSequenceType sequenceType;
    private String redirectUrl;
    private String webhookUrl;
    @JsonProperty("_links")
    private MollieLinks links;

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public MollieAmount getAmount() {
        return amount;
    }

    public void setAmount(MollieAmount amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String mehod) {
        this.method = mehod;
    }

    public Object getMetadata() {
        return metadata;
    }

    public void setMetadata(Object metadata) {
        this.metadata = metadata;
    }

    public MolliePaymentStatus getStatus() {
        return status;
    }

    public void setStatus(MolliePaymentStatus status) {
        this.status = status;
    }

    public Boolean getIsCancelable() {
        return isCancelable;
    }

    public void setIsCancelable(Boolean cancelable) {
        isCancelable = cancelable;
    }

    public String getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(String expiresAt) {
        this.expiresAt = expiresAt;
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public MollieSequenceType getSequenceType() {
        return sequenceType;
    }

    public void setSequenceType(MollieSequenceType sequenceType) {
        this.sequenceType = sequenceType;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public String getWebhookUrl() {
        return webhookUrl;
    }

    public void setWebhookUrl(String webhookUrl) {
        this.webhookUrl = webhookUrl;
    }

    public MollieLinks getLinks() {
        return links;
    }

    public void setLinks(MollieLinks links) {
        this.links = links;
    }
}

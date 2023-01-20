package nl.nubilus.mollie.api.payment;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MolliePaymentRequest {
    private MollieAmount amount;
    private List<MolliePaymentMethod> method;
    private String description;
    private String redirectUrl;
    private String cancelUrl;
    private String webhookUrl;
    private String locale;
    private String restrictPaymentMethodsToCountry;
    private Object metadata;
    private String customerId;

    /**
     * recurring payments
     */
    private MollieSequenceType sequenceType;

    /**
     * recurring payments
     */
    private String mandateId;

    /**
     * Apple Pay
     */
    private String applePayPaymentToken;
    /**
     * Bank transfer and Przelewy24
     */
    private String billingEmail;
    /**
     * Bank transfer
     */
    private String dueDate;
    /**
     * Credit card
     */
    private MollieAddress billingAddress;
    /**
     * Credit card
     */
    private String cardToken;
    /**
     * Credit card and PayPal
     */
    private MollieAddress shippingAddress;
    /**
     * Gift cards and IDEAL and Vouchers
     */
    private String issuer;
    /**
     * Gift cards
     */
    private String voucherNumber;
    /**
     * Gift cards
     */
    private String voucherPin;

    /**
     *
     * Klarna Pay now. / Pay later. / Slice it
     */
    private Object extraMerchantData;

    /**
     * PayPal
     */
    private String sessionId;

    /**
     * PayPal
     */
    private Boolean digitalGoods;

    /**
     * paysafecard
     */
    private String customerReference;

    /**
     * SEPA Direct Debit
     */
    private String consumerName;

    /**
     * SEPA Direct Debit
     */
    private String consumerAccount;

    public MollieAmount getAmount() {
        return amount;
    }

    public void setAmount(MollieAmount amount) {
        this.amount = amount;
    }

    public List<MolliePaymentMethod> getMethod() {
        return method;
    }

    public void setMethod(List<MolliePaymentMethod> method) {
        this.method = method;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public String getCancelUrl() {
        return cancelUrl;
    }

    public void setCancelUrl(String cancelUrl) {
        this.cancelUrl = cancelUrl;
    }

    public String getWebhookUrl() {
        return webhookUrl;
    }

    public void setWebhookUrl(String webhookUrl) {
        this.webhookUrl = webhookUrl;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getRestrictPaymentMethodsToCountry() {
        return restrictPaymentMethodsToCountry;
    }

    public void setRestrictPaymentMethodsToCountry(String restrictPaymentMethodsToCountry) {
        this.restrictPaymentMethodsToCountry = restrictPaymentMethodsToCountry;
    }

    public Object getMetadata() {
        return metadata;
    }

    public void setMetadata(Object metadata) {
        this.metadata = metadata;
    }

    /**
     * recurring payments
     */
    public MollieSequenceType getSequenceType() {
        return sequenceType;
    }

    /**
     * recurring payments
     */
    public void setSequenceType(MollieSequenceType sequenceType) {
        this.sequenceType = sequenceType;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    /**
     * recurring payments
     */
    public String getMandateId() {
        return mandateId;
    }

    /**
     * recurring payments
     */
    public void setMandateId(String mandateId) {
        this.mandateId = mandateId;
    }

    /**
     * Apple payment
     */
    public String getApplePayPaymentToken() {
        return applePayPaymentToken;
    }

    /**
     * Apple payment
     */

    public void setApplePayPaymentToken(String applePayPaymentToken) {
        this.applePayPaymentToken = applePayPaymentToken;
    }

    /**
     * Bank transfer and Przelewy24
     */
    public String getBillingEmail() {
        return billingEmail;
    }

    /**
     * Bank transfer and Przelewy24
     */
    public void setBillingEmail(String billingEmail) {
        this.billingEmail = billingEmail;
    }

    /**
     * Bank transfer.
     * The date the payment should expire, in YYYY-MM-DD format. Note: the minimum date is tomorrow and the maximum date is 100 days after tomorrow.
     */
    public String getDueDate() {
        return dueDate;
    }

    /**
     * Bank transfer.
     * The date the payment should expire, in YYYY-MM-DD format. Note: the minimum date is tomorrow and the maximum date is 100 days after tomorrow.
     */
    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * Credit card
     */
    public MollieAddress getBillingAddress() {
        return billingAddress;
    }

    /**
     * Credit card
     */
    public void setBillingAddress(MollieAddress billingAddress) {
        this.billingAddress = billingAddress;
    }
    /**
     * Credit card
     */
    public String getCardToken() {
        return cardToken;
    }
    /**
     * Credit card
     */
    public void setCardToken(String cardToken) {
        this.cardToken = cardToken;
    }
    /**
     * Credit card
     */
    public MollieAddress getShippingAddress() {
        return shippingAddress;
    }
    /**
     * Credit card
     */
    public void setShippingAddress(MollieAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
    /**
     * Gift cards and iDEAL and Vouchers
     */
    public String getIssuer() {
        return issuer;
    }
    /**
     * Gift cards and iDEAL and Vouchers
     */
    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }
    /**
     * Gift cards
     */
    public String getVoucherNumber() {
        return voucherNumber;
    }
    /**
     * Gift cards
     */
    public void setVoucherNumber(String voucherNumber) {
        this.voucherNumber = voucherNumber;
    }
    /**
     * Gift cards
     */
    public String getVoucherPin() {
        return voucherPin;
    }
    /**
     * Gift cards
     */
    public void setVoucherPin(String voucherPin) {
        this.voucherPin = voucherPin;
    }
    /**
     *
     * Klarna Pay now. / Pay later. / Slice it
     */
    public Object getExtraMerchantData() {
        return extraMerchantData;
    }
    /**
     *
     * Klarna Pay now. / Pay later. / Slice it
     */
    public void setExtraMerchantData(Object extraMerchantData) {
        this.extraMerchantData = extraMerchantData;
    }

    /**
     * PayPal
     */
    public String getSessionId() {
        return sessionId;
    }

    /**
     * PayPal
     */
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
    /**
     * PayPal
     */
    public Boolean getDigitalGoods() {
        return digitalGoods;
    }
    /**
     * PayPal
     */
    public void setDigitalGoods(Boolean digitalGoods) {
        this.digitalGoods = digitalGoods;
    }
    /**
     * paysafecard
     */
    public String getCustomerReference() {
        return customerReference;
    }
    /**
     * paysafecard
     */
    public void setCustomerReference(String customerReference) {
        this.customerReference = customerReference;
    }
    /**
     * SEPA Direct Debit
     */
    public String getConsumerName() {
        return consumerName;
    }
    /**
     * SEPA Direct Debit
     */
    public void setConsumerName(String consumerName) {
        this.consumerName = consumerName;
    }
    /**
     * SEPA Direct Debit
     */
    public String getConsumerAccount() {
        return consumerAccount;
    }
    /**
     * SEPA Direct Debit
     */
    public void setConsumerAccount(String consumerAccount) {
        this.consumerAccount = consumerAccount;
    }
}

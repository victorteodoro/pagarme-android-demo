
package me.pagar.demoandroidpagarmempos.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Transaction {

    @SerializedName("object")
    @Expose
    private String object;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("refuse_reason")
    @Expose
    private Object refuseReason;
    @SerializedName("status_reason")
    @Expose
    private String statusReason;
    @SerializedName("acquirer_response_code")
    @Expose
    private String acquirerResponseCode;
    @SerializedName("acquirer_name")
    @Expose
    private String acquirerName;
    @SerializedName("acquirer_id")
    @Expose
    private String acquirerId;
    @SerializedName("authorization_code")
    @Expose
    private String authorizationCode;
    @SerializedName("soft_descriptor")
    @Expose
    private String softDescriptor;
    @SerializedName("tid")
    @Expose
    private Integer tid;
    @SerializedName("nsu")
    @Expose
    private Integer nsu;
    @SerializedName("date_created")
    @Expose
    private String dateCreated;
    @SerializedName("date_updated")
    @Expose
    private String dateUpdated;
    @SerializedName("amount")
    @Expose
    private Integer amount;
    @SerializedName("authorized_amount")
    @Expose
    private Integer authorizedAmount;
    @SerializedName("paid_amount")
    @Expose
    private Integer paidAmount;
    @SerializedName("refunded_amount")
    @Expose
    private Integer refundedAmount;
    @SerializedName("installments")
    @Expose
    private Integer installments;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("cost")
    @Expose
    private Integer cost;
    @SerializedName("card_holder_name")
    @Expose
    private String cardHolderName;
    @SerializedName("card_last_digits")
    @Expose
    private String cardLastDigits;
    @SerializedName("card_first_digits")
    @Expose
    private String cardFirstDigits;
    @SerializedName("card_brand")
    @Expose
    private String cardBrand;
    @SerializedName("card_pin_mode")
    @Expose
    private Object cardPinMode;
    @SerializedName("postback_url")
    @Expose
    private Object postbackUrl;
    @SerializedName("payment_method")
    @Expose
    private String paymentMethod;
    @SerializedName("capture_method")
    @Expose
    private String captureMethod;
    @SerializedName("antifraud_score")
    @Expose
    private Object antifraudScore;
    @SerializedName("boleto_url")
    @Expose
    private Object boletoUrl;
    @SerializedName("boleto_barcode")
    @Expose
    private Object boletoBarcode;
    @SerializedName("boleto_expiration_date")
    @Expose
    private Object boletoExpirationDate;
    @SerializedName("referer")
    @Expose
    private String referer;
    @SerializedName("ip")
    @Expose
    private String ip;
    @SerializedName("subscription_id")
    @Expose
    private Object subscriptionId;
    @SerializedName("phone")
    @Expose
    private Object phone;
    @SerializedName("address")
    @Expose
    private Object address;
    @SerializedName("customer")
    @Expose
    private Object customer;
    @SerializedName("billing")
    @Expose
    private Object billing;
    @SerializedName("shipping")
    @Expose
    private Object shipping;
    @SerializedName("items")
    @Expose
    private List<Object> items = null;
    @SerializedName("card")
    @Expose
    private Card card;
    @SerializedName("split_rules")
    @Expose
    private List<SplitRule> splitRules = null;
    @SerializedName("antifraud_metadata")
    @Expose
    private AntifraudMetadata antifraudMetadata;
    @SerializedName("reference_key")
    @Expose
    private Object referenceKey;
    @SerializedName("device")
    @Expose
    private Object device;
    @SerializedName("local_transaction_id")
    @Expose
    private Object localTransactionId;
    @SerializedName("local_time")
    @Expose
    private Object localTime;
    @SerializedName("metadata")
    @Expose
    private Metadata metadata;

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getRefuseReason() {
        return refuseReason;
    }

    public void setRefuseReason(Object refuseReason) {
        this.refuseReason = refuseReason;
    }

    public String getStatusReason() {
        return statusReason;
    }

    public void setStatusReason(String statusReason) {
        this.statusReason = statusReason;
    }

    public String getAcquirerResponseCode() {
        return acquirerResponseCode;
    }

    public void setAcquirerResponseCode(String acquirerResponseCode) {
        this.acquirerResponseCode = acquirerResponseCode;
    }

    public String getAcquirerName() {
        return acquirerName;
    }

    public void setAcquirerName(String acquirerName) {
        this.acquirerName = acquirerName;
    }

    public String getAcquirerId() {
        return acquirerId;
    }

    public void setAcquirerId(String acquirerId) {
        this.acquirerId = acquirerId;
    }

    public String getAuthorizationCode() {
        return authorizationCode;
    }

    public void setAuthorizationCode(String authorizationCode) {
        this.authorizationCode = authorizationCode;
    }

    public String getSoftDescriptor() {
        return softDescriptor;
    }

    public void setSoftDescriptor(String softDescriptor) {
        this.softDescriptor = softDescriptor;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Integer getNsu() {
        return nsu;
    }

    public void setNsu(Integer nsu) {
        this.nsu = nsu;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(String dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getAuthorizedAmount() {
        return authorizedAmount;
    }

    public void setAuthorizedAmount(Integer authorizedAmount) {
        this.authorizedAmount = authorizedAmount;
    }

    public Integer getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(Integer paidAmount) {
        this.paidAmount = paidAmount;
    }

    public Integer getRefundedAmount() {
        return refundedAmount;
    }

    public void setRefundedAmount(Integer refundedAmount) {
        this.refundedAmount = refundedAmount;
    }

    public Integer getInstallments() {
        return installments;
    }

    public void setInstallments(Integer installments) {
        this.installments = installments;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getCardLastDigits() {
        return cardLastDigits;
    }

    public void setCardLastDigits(String cardLastDigits) {
        this.cardLastDigits = cardLastDigits;
    }

    public String getCardFirstDigits() {
        return cardFirstDigits;
    }

    public void setCardFirstDigits(String cardFirstDigits) {
        this.cardFirstDigits = cardFirstDigits;
    }

    public String getCardBrand() {
        return cardBrand;
    }

    public void setCardBrand(String cardBrand) {
        this.cardBrand = cardBrand;
    }

    public Object getCardPinMode() {
        return cardPinMode;
    }

    public void setCardPinMode(Object cardPinMode) {
        this.cardPinMode = cardPinMode;
    }

    public Object getPostbackUrl() {
        return postbackUrl;
    }

    public void setPostbackUrl(Object postbackUrl) {
        this.postbackUrl = postbackUrl;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getCaptureMethod() {
        return captureMethod;
    }

    public void setCaptureMethod(String captureMethod) {
        this.captureMethod = captureMethod;
    }

    public Object getAntifraudScore() {
        return antifraudScore;
    }

    public void setAntifraudScore(Object antifraudScore) {
        this.antifraudScore = antifraudScore;
    }

    public Object getBoletoUrl() {
        return boletoUrl;
    }

    public void setBoletoUrl(Object boletoUrl) {
        this.boletoUrl = boletoUrl;
    }

    public Object getBoletoBarcode() {
        return boletoBarcode;
    }

    public void setBoletoBarcode(Object boletoBarcode) {
        this.boletoBarcode = boletoBarcode;
    }

    public Object getBoletoExpirationDate() {
        return boletoExpirationDate;
    }

    public void setBoletoExpirationDate(Object boletoExpirationDate) {
        this.boletoExpirationDate = boletoExpirationDate;
    }

    public String getReferer() {
        return referer;
    }

    public void setReferer(String referer) {
        this.referer = referer;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Object getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(Object subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public Object getPhone() {
        return phone;
    }

    public void setPhone(Object phone) {
        this.phone = phone;
    }

    public Object getAddress() {
        return address;
    }

    public void setAddress(Object address) {
        this.address = address;
    }

    public Object getCustomer() {
        return customer;
    }

    public void setCustomer(Object customer) {
        this.customer = customer;
    }

    public Object getBilling() {
        return billing;
    }

    public void setBilling(Object billing) {
        this.billing = billing;
    }

    public Object getShipping() {
        return shipping;
    }

    public void setShipping(Object shipping) {
        this.shipping = shipping;
    }

    public List<Object> getItems() {
        return items;
    }

    public void setItems(List<Object> items) {
        this.items = items;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public List<SplitRule> getSplitRules() {
        return splitRules;
    }

    public void setSplitRules(List<SplitRule> splitRules) {
        this.splitRules = splitRules;
    }

    public AntifraudMetadata getAntifraudMetadata() {
        return antifraudMetadata;
    }

    public void setAntifraudMetadata(AntifraudMetadata antifraudMetadata) {
        this.antifraudMetadata = antifraudMetadata;
    }

    public Object getReferenceKey() {
        return referenceKey;
    }

    public void setReferenceKey(Object referenceKey) {
        this.referenceKey = referenceKey;
    }

    public Object getDevice() {
        return device;
    }

    public void setDevice(Object device) {
        this.device = device;
    }

    public Object getLocalTransactionId() {
        return localTransactionId;
    }

    public void setLocalTransactionId(Object localTransactionId) {
        this.localTransactionId = localTransactionId;
    }

    public Object getLocalTime() {
        return localTime;
    }

    public void setLocalTime(Object localTime) {
        this.localTime = localTime;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

}

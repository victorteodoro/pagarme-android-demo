
package me.pagar.demoandroidpagarmempos.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SplitRule {

    @SerializedName("object")
    @Expose
    private String object;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("liable")
    @Expose
    private Boolean liable;
    @SerializedName("amount")
    @Expose
    private Object amount;
    @SerializedName("percentage")
    @Expose
    private Integer percentage;
    @SerializedName("recipient_id")
    @Expose
    private String recipientId;
    @SerializedName("charge_remainder")
    @Expose
    private Boolean chargeRemainder;
    @SerializedName("charge_processing_fee")
    @Expose
    private Boolean chargeProcessingFee;
    @SerializedName("date_created")
    @Expose
    private String dateCreated;
    @SerializedName("date_updated")
    @Expose
    private String dateUpdated;

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getLiable() {
        return liable;
    }

    public void setLiable(Boolean liable) {
        this.liable = liable;
    }

    public Object getAmount() {
        return amount;
    }

    public void setAmount(Object amount) {
        this.amount = amount;
    }

    public Integer getPercentage() {
        return percentage;
    }

    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
    }

    public String getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(String recipientId) {
        this.recipientId = recipientId;
    }

    public Boolean getChargeRemainder() {
        return chargeRemainder;
    }

    public void setChargeRemainder(Boolean chargeRemainder) {
        this.chargeRemainder = chargeRemainder;
    }

    public Boolean getChargeProcessingFee() {
        return chargeProcessingFee;
    }

    public void setChargeProcessingFee(Boolean chargeProcessingFee) {
        this.chargeProcessingFee = chargeProcessingFee;
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

}

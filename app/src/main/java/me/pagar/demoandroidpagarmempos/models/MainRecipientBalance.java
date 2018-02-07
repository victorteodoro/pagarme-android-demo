
package me.pagar.demoandroidpagarmempos.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MainRecipientBalance {

    @SerializedName("object")
    @Expose
    private String object;
    @SerializedName("waiting_funds")
    @Expose
    private WaitingFunds waitingFunds;
    @SerializedName("transferred")
    @Expose
    private Transferred transferred;
    @SerializedName("available")
    @Expose
    private Available available;

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public WaitingFunds getWaitingFunds() {
        return waitingFunds;
    }

    public void setWaitingFunds(WaitingFunds waitingFunds) {
        this.waitingFunds = waitingFunds;
    }

    public Transferred getTransferred() {
        return transferred;
    }

    public void setTransferred(Transferred transferred) {
        this.transferred = transferred;
    }

    public Available getAvailable() {
        return available;
    }

    public void setAvailable(Available available) {
        this.available = available;
    }

}

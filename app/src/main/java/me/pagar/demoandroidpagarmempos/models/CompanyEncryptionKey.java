
package me.pagar.demoandroidpagarmempos.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CompanyEncryptionKey {

    @SerializedName("test")
    @Expose
    private String test;
    @SerializedName("live")
    @Expose
    private String live;

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public String getLive() {
        return live;
    }

    public void setLive(String live) {
        this.live = live;
    }

}

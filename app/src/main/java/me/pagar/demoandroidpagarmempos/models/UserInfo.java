
package me.pagar.demoandroidpagarmempos.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserInfo {

    @SerializedName("user_object")
    @Expose
    private String userObject;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("user_email")
    @Expose
    private String userEmail;
    @SerializedName("user_name")
    @Expose
    private String userName;
    @SerializedName("user_permission")
    @Expose
    private String userPermission;
    @SerializedName("user_date_created")
    @Expose
    private String userDateCreated;
    @SerializedName("company_id")
    @Expose
    private String companyId;
    @SerializedName("company_encryption_key")
    @Expose
    private CompanyEncryptionKey companyEncryptionKey;
    @SerializedName("company_name")
    @Expose
    private String companyName;
    @SerializedName("company_status")
    @Expose
    private String companyStatus;
    @SerializedName("session_id")
    @Expose
    private String sessionId;

    public String getUserObject() {
        return userObject;
    }

    public void setUserObject(String userObject) {
        this.userObject = userObject;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPermission() {
        return userPermission;
    }

    public void setUserPermission(String userPermission) {
        this.userPermission = userPermission;
    }

    public String getUserDateCreated() {
        return userDateCreated;
    }

    public void setUserDateCreated(String userDateCreated) {
        this.userDateCreated = userDateCreated;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public CompanyEncryptionKey getCompanyEncryptionKey() {
        return companyEncryptionKey;
    }

    public void setCompanyEncryptionKey(CompanyEncryptionKey companyEncryptionKey) {
        this.companyEncryptionKey = companyEncryptionKey;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyStatus() {
        return companyStatus;
    }

    public void setCompanyStatus(String companyStatus) {
        this.companyStatus = companyStatus;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

}

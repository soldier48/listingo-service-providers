package com.tregix.serviceprovider.Model.Login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class Data_ extends RealmObject {

    @SerializedName("ID")
    @Expose
    private String iD;
    @SerializedName("user_login")
    @Expose
    private String userLogin;
    @SerializedName("user_pass")
    @Expose
    private String userPass;
    @SerializedName("user_nicename")
    @Expose
    private String userNicename;
    @SerializedName("user_email")
    @Expose
    private String userEmail;
    @SerializedName("user_url")
    @Expose
    private String userUrl;
    @SerializedName("user_registered")
    @Expose
    private String userRegistered;
    @SerializedName("user_activation_key")
    @Expose
    private String userActivationKey;
    @SerializedName("user_status")
    @Expose
    private String userStatus;
    @SerializedName("display_name")
    @Expose
    private String displayName;
    @SerializedName("meta")
    @Expose
    private Meta meta;
    private String avatar;
    private String banner;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public String getID() {
        return iD;
    }

    public void setID(String iD) {
        this.iD = iD;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getUserNicename() {
        return userNicename;
    }

    public void setUserNicename(String userNicename) {
        this.userNicename = userNicename;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserUrl() {
        return userUrl;
    }

    public void setUserUrl(String userUrl) {
        this.userUrl = userUrl;
    }

    public String getUserRegistered() {
        return userRegistered;
    }

    public void setUserRegistered(String userRegistered) {
        this.userRegistered = userRegistered;
    }

    public String getUserActivationKey() {
        return userActivationKey;
    }

    public void setUserActivationKey(String userActivationKey) {
        this.userActivationKey = userActivationKey;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

}

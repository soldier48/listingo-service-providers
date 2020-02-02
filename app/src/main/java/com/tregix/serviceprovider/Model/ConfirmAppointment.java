package com.tregix.serviceprovider.Model;

/**
 * Created by Gohar Ali on 1/31/2018.
 */


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ConfirmAppointment {

    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("apt_services")
    @Expose
    private String aptServices;
    @SerializedName("apt_types")
    @Expose
    private String aptTypes;
    @SerializedName("apt_reasons")
    @Expose
    private String aptReasons;
    @SerializedName("apt_description")
    @Expose
    private String aptDescription;
    @SerializedName("apt_name")
    @Expose
    private String aptName;
    @SerializedName("apt_mobile")
    @Expose
    private String aptMobile;
    @SerializedName("apt_email")
    @Expose
    private String aptEmail;
    @SerializedName("apt_currency_symbol")
    @Expose
    private String aptCurrencySymbol;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAptServices() {
        return aptServices;
    }

    public void setAptServices(String aptServices) {
        this.aptServices = aptServices;
    }

    public String getAptTypes() {
        return aptTypes;
    }

    public void setAptTypes(String aptTypes) {
        this.aptTypes = aptTypes;
    }

    public String getAptReasons() {
        return aptReasons;
    }

    public void setAptReasons(String aptReasons) {
        this.aptReasons = aptReasons;
    }

    public String getAptDescription() {
        return aptDescription;
    }

    public void setAptDescription(String aptDescription) {
        this.aptDescription = aptDescription;
    }

    public String getAptName() {
        return aptName;
    }

    public void setAptName(String aptName) {
        this.aptName = aptName;
    }

    public String getAptMobile() {
        return aptMobile;
    }

    public void setAptMobile(String aptMobile) {
        this.aptMobile = aptMobile;
    }

    public String getAptEmail() {
        return aptEmail;
    }

    public void setAptEmail(String aptEmail) {
        this.aptEmail = aptEmail;
    }

    public String getAptCurrencySymbol() {
        return aptCurrencySymbol;
    }

    public void setAptCurrencySymbol(String aptCurrencySymbol) {
        this.aptCurrencySymbol = aptCurrencySymbol;
    }

}
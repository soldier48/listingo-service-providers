package com.tregix.serviceprovider.Model;

/**
 * Created by Gohar Ali on 12/22/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterSeeker {

    @SerializedName("register[type]")
    @Expose
    private String registerType;
    @SerializedName("register[category]")
    @Expose
    private String registerCategory;
    @SerializedName("register[sub_category]")
    @Expose
    private String registerSubCategory;
    @SerializedName("register[username]")
    @Expose
    private String registerUsername;
    @SerializedName("register[company_name]")
    @Expose
    private String registerCompanyName;
    @SerializedName("register[phone]")
    @Expose
    private String registerPhone;
    @SerializedName("register[email]")
    @Expose
    private String registerEmail;
    @SerializedName("register[password]")
    @Expose
    private String registerPassword;
    @SerializedName("register[confirm_password")
    @Expose
    private String registerConfirmPassword;
    @SerializedName("register[account]")
    @Expose
    private String registerAccount;

    public String getRegisterType() {
        return registerType;
    }

    public void setRegisterType(String registerType) {
        this.registerType = registerType;
    }

    public String getRegisterCategory() {
        return registerCategory;
    }

    public void setRegisterCategory(String registerCategory) {
        this.registerCategory = registerCategory;
    }

    public String getRegisterSubCategory() {
        return registerSubCategory;
    }

    public void setRegisterSubCategory(String registerSubCategory) {
        this.registerSubCategory = registerSubCategory;
    }

    public String getRegisterUsername() {
        return registerUsername;
    }

    public void setRegisterUsername(String registerUsername) {
        this.registerUsername = registerUsername;
    }

    public String getRegisterCompanyName() {
        return registerCompanyName;
    }

    public void setRegisterCompanyName(String registerCompanyName) {
        this.registerCompanyName = registerCompanyName;
    }

    public String getRegisterPhone() {
        return registerPhone;
    }

    public void setRegisterPhone(String registerPhone) {
        this.registerPhone = registerPhone;
    }

    public String getRegisterEmail() {
        return registerEmail;
    }

    public void setRegisterEmail(String registerEmail) {
        this.registerEmail = registerEmail;
    }

    public String getRegisterPassword() {
        return registerPassword;
    }

    public void setRegisterPassword(String registerPassword) {
        this.registerPassword = registerPassword;
    }

    public String getRegisterConfirmPassword() {
        return registerConfirmPassword;
    }

    public void setRegisterConfirmPassword(String registerConfirmPassword) {
        this.registerConfirmPassword = registerConfirmPassword;
    }

    public String getRegisterAccount() {
        return registerAccount;
    }

    public void setRegisterAccount(String registerAccount) {
        this.registerAccount = registerAccount;
    }

}

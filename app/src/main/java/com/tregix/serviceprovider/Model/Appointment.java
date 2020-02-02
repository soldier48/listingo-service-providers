package com.tregix.serviceprovider.Model;

import android.text.Html;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tregix.serviceprovider.Utils.AppUtils;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Gohar Ali on 2/2/2018.
 */

public class Appointment implements Serializable{

    @SerializedName("post_id")
    @Expose
    private int postId;
    @SerializedName("apt_types")
    @Expose
    private String aptTypes;
    @SerializedName("apt_services")
    @Expose
    private String aptServices;
    @SerializedName("apt_reasons")
    @Expose
    private String aptReasons;
    @SerializedName("apt_description")
    @Expose
    private String aptDescription;
    @SerializedName("apt_currency_symbol")
    @Expose
    private String aptCurrencySymbol;
    @SerializedName("apt_user_to")
    @Expose
    private String aptUserTo;
    @SerializedName("provider")
    @Expose
    private String provider;
    @SerializedName("apt_date")
    @Expose
    private String aptDate;
    @SerializedName("apt_time")
    @Expose
    private String aptTime;
    @SerializedName("time")
    @Expose
    private List<String> time = null;
    @SerializedName("apt_user_from")
    @Expose
    private String aptUserFrom;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("avatar")
    @Expose
    private String avatar;

    private String status;

    public String getAptTypes() {
        return aptTypes;
    }

    public void setAptTypes(String aptTypes) {
        this.aptTypes = aptTypes;
    }

    public String getAptServices() {
        return aptServices;
    }

    public void setAptServices(String aptServices) {
        this.aptServices = aptServices;
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

    public String getAptCurrencySymbol() {
        return aptCurrencySymbol;
    }

    public void setAptCurrencySymbol(String aptCurrencySymbol) {
        this.aptCurrencySymbol = aptCurrencySymbol;
    }

    public String getAptUserTo() {
        return aptUserTo;
    }

    public void setAptUserTo(String aptUserTo) {
        this.aptUserTo = aptUserTo;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getAptDate() {
        return aptDate;
    }

    public void setAptDate(String aptDate) {
        this.aptDate = aptDate;
    }

    public String getAptTime() {
        return aptTime;
    }

    public void setAptTime(String aptTime) {
        this.aptTime = aptTime;
    }

    public List<String> getTime() {
        return time;
    }

    public void setTime(List<String> time) {
        this.time = time;
    }

    public String getAptUserFrom() {
        return aptUserFrom;
    }

    public void setAptUserFrom(String aptUserFrom) {
        this.aptUserFrom = aptUserFrom;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        String title = "<h2><b>Appointment Summary</b></h2> <br></br>";

        String data =  " <strong>Provider : </strong> <span>"+provider+"</span> <br></br><br></br>" +
                " <strong>Date : </strong> <span>"+ AppUtils.longToDate(aptDate,"MMM dd, yyyy",1000)+"</span><br></br> <br></br>"+
                " <strong>Time : </strong> <span>"+time.get(0)+" - "+time.get(1)+"</span> <br></br><br></br>"+
                " <strong>Type : </strong> <span>"+aptTypes+"</span><br></br> <br></br>"+
                " <strong>Service : </strong> <span>"+aptServices+"</span> <br></br><br></br>"+
                " <strong>Reason : </strong> <span>"+aptReasons+"</span> <br></br><br></br>"+
                " <strong>Description : </strong> <span>"+aptDescription+"</span> <br></br>";

        return Html.fromHtml(title+data).toString();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPostId() {
        return postId;
    }
}



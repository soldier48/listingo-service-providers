package com.tregix.serviceprovider.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tregix.serviceprovider.Model.Provider.BusinessHours;
import com.tregix.serviceprovider.Model.Provider.PrivacySettings;

/**
 * Created by Confiz123 on 3/19/2018.
 */

public class ManagePrivacyRequest {

    @SerializedName("publisher_id")
    @Expose
    private int publisherId;

    @SerializedName("privacy_settings")
    @Expose
    private PrivacySettings privacySettings;

    @SerializedName("business_hours")
    @Expose
    private BusinessHours businessHours;

    public ManagePrivacyRequest(int id, PrivacySettings settings){
        publisherId = id;
        privacySettings = settings;
    }

    public ManagePrivacyRequest(int id, BusinessHours settings){
        publisherId = id;
        businessHours = settings;
    }

    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }

    public PrivacySettings getPrivacySettings() {
        return privacySettings;
    }

    public void setPrivacySettings(PrivacySettings privacySettings) {
        this.privacySettings = privacySettings;
    }

}

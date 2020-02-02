package com.tregix.serviceprovider.Model.Login;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by Gohar Ali on 1/31/2018.
 */

public class Meta extends RealmObject {

    @SerializedName("phone")
    @Expose
    private RealmList<String> phone = null;

    @SerializedName("verify_user")
    @Expose
    private RealmList<String> verifyUser = null;

    @SerializedName("activation_status")
    @Expose
    private RealmList<String> activeStatus = null;

    public RealmList<String> getPhone() {
        return phone;
    }

    public void setPhone(RealmList<String> phone) {
        this.phone = phone;
    }

    public RealmList<String> getVerification() {
        return verifyUser;
    }

    public RealmList<String> getActiveStatus() {
        return activeStatus;
    }
}
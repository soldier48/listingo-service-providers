package com.tregix.serviceprovider.Model.Login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class Caps extends RealmObject {

    @SerializedName("customer")
    @Expose
    private Boolean customer;
    @SerializedName("bbp_participant")
    @Expose
    private Boolean bbpParticipant;

    public Boolean getCustomer() {
        return customer;
    }

    public void setCustomer(Boolean customer) {
        this.customer = customer;
    }

    public Boolean getBbpParticipant() {
        return bbpParticipant;
    }

    public void setBbpParticipant(Boolean bbpParticipant) {
        this.bbpParticipant = bbpParticipant;
    }

}

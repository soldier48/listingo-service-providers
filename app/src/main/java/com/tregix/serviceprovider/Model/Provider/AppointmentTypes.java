
package com.tregix.serviceprovider.Model.Provider;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AppointmentTypes implements Serializable {

    @SerializedName("newappointment")
    @Expose
    private String newappointment;
    @SerializedName("followup")
    @Expose
    private String followup;
    @SerializedName("others")
    @Expose
    private String others;

    public String getNewappointment() {
        return newappointment;
    }

    public void setNewappointment(String newappointment) {
        this.newappointment = newappointment;
    }

    public String getFollowup() {
        return followup;
    }

    public void setFollowup(String followup) {
        this.followup = followup;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }

}

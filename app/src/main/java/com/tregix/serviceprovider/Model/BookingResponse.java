package com.tregix.serviceprovider.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Gohar Ali on 1/4/2018.
 */

public class BookingResponse {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("appointment_link")
    @Expose
    private String appointmentLink;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAppointmentLink() {
        return appointmentLink;
    }

    public void setAppointmentLink(String appointmentLink) {
        this.appointmentLink = appointmentLink;
    }
}
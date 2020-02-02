package com.tregix.serviceprovider.Model;

/**
 * Created by Gohar Ali on 1/31/2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiResponse {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("appt_data")
    @Expose
    private String apptData;
    @SerializedName("message")
    @Expose
    private String message;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getApptData() {
        return apptData;
    }

    public void setApptData(String apptData) {
        this.apptData = apptData;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}


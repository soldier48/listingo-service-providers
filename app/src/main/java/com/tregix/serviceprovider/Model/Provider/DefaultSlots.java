
package com.tregix.serviceprovider.Model.Provider;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DefaultSlots implements Serializable {

    @SerializedName("monday")
    @Expose
    private Monday_ monday;
    @SerializedName("monday-details")
    @Expose
    private Object mondayDetails;
    @SerializedName("tuesday")
    @Expose
    private Tuesday_ tuesday;
    @SerializedName("tuesday-details")
    @Expose
    private Object tuesdayDetails;
    @SerializedName("wednesday")
    @Expose
    private Wednesday_ wednesday;
    @SerializedName("wednesday-details")
    @Expose
    private Object wednesdayDetails;
    @SerializedName("thursday")
    @Expose
    private Thursday_ thursday;
    @SerializedName("thursday-details")
    @Expose
    private Object thursdayDetails;
    @SerializedName("friday")
    @Expose
    private Friday_ friday;
    @SerializedName("friday-details")
    @Expose
    private Object fridayDetails;

    public Monday_ getMonday() {
        return monday;
    }

    public void setMonday(Monday_ monday) {
        this.monday = monday;
    }

    public Object getMondayDetails() {
        return mondayDetails;
    }

    public void setMondayDetails(Object mondayDetails) {
        this.mondayDetails = mondayDetails;
    }

    public Tuesday_ getTuesday() {
        return tuesday;
    }

    public void setTuesday(Tuesday_ tuesday) {
        this.tuesday = tuesday;
    }

    public Object getTuesdayDetails() {
        return tuesdayDetails;
    }

    public void setTuesdayDetails(Object tuesdayDetails) {
        this.tuesdayDetails = tuesdayDetails;
    }

    public Wednesday_ getWednesday() {
        return wednesday;
    }

    public void setWednesday(Wednesday_ wednesday) {
        this.wednesday = wednesday;
    }

    public Object getWednesdayDetails() {
        return wednesdayDetails;
    }

    public void setWednesdayDetails(Object wednesdayDetails) {
        this.wednesdayDetails = wednesdayDetails;
    }

    public Thursday_ getThursday() {
        return thursday;
    }

    public void setThursday(Thursday_ thursday) {
        this.thursday = thursday;
    }

    public Object getThursdayDetails() {
        return thursdayDetails;
    }

    public void setThursdayDetails(Object thursdayDetails) {
        this.thursdayDetails = thursdayDetails;
    }

    public Friday_ getFriday() {
        return friday;
    }

    public void setFriday(Friday_ friday) {
        this.friday = friday;
    }

    public Object getFridayDetails() {
        return fridayDetails;
    }

    public void setFridayDetails(Object fridayDetails) {
        this.fridayDetails = fridayDetails;
    }

}

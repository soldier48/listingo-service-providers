package com.tregix.serviceprovider.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Gohar Ali on 1/4/2018.
 */

public class BookingRequest {

    @SerializedName("tg-timeslot")
    @Expose
    String timeSlot;
    @SerializedName("slot_date")
    @Expose
    String dateSlot;
    @SerializedName("author_id")
    @Expose
    String authorId;

    @SerializedName("user_id")
    @Expose
    String userId;

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public String getDateSlot() {
        return dateSlot;
    }

    public void setDateSlot(String dateSlot) {
        this.dateSlot = dateSlot;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


}

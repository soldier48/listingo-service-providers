package com.tregix.serviceprovider.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Gohar Ali on 2/12/2018.
 */

public class RequestSlots {

    @SerializedName("slot_date")
    @Expose
    private String slotDate;
    @SerializedName("author_id")
    @Expose
    private String authorId;

    public RequestSlots(String slotDate,String authorId){
        this.slotDate = slotDate;
        this.authorId = authorId;
    }

    public String getSlotDate() {
        return slotDate;
    }

    public void setSlotDate(String slotDate) {
        this.slotDate = slotDate;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

}

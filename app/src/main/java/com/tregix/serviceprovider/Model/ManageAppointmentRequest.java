package com.tregix.serviceprovider.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Confiz123 on 3/18/2018.
 */

public class ManageAppointmentRequest {

    @SerializedName("rejection_title")
    @Expose
    private String rejectionTitle;

    @SerializedName("rejection_reason")
    @Expose
    private String rejectionReason;
    @SerializedName("post_id")
    @Expose
    private int postId;

    private String action;

    @SerializedName("publisher_id")
    @Expose
    private int publisherId;

    public ManageAppointmentRequest(){

    }
    
    public ManageAppointmentRequest(int postId, String action, int publisherId){
        this.postId = postId;
        this.publisherId = publisherId;
        this.action = action;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }

    public String getRejectionTitle() {
        return rejectionTitle;
    }

    public void setRejectionTitle(String rejectionTitle) {
        this.rejectionTitle = rejectionTitle;
    }

    public String getrejectionReason() {
        return rejectionReason;
    }

    public void setrejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }

    public int getpostId() {
        return postId;
    }

    public void setpostId(int postId) {
        this.postId = postId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}

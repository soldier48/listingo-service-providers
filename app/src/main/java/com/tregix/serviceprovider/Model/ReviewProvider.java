package com.tregix.serviceprovider.Model;

/**
 * Created by Confiz123 on 3/6/2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class ReviewProvider {

    @SerializedName("review_wait_time")
    @Expose
    private String reviewWaitTime;
    @SerializedName("review_title")
    @Expose
    private String reviewTitle;
    @SerializedName("review_description")
    @Expose
    private String reviewDescription;
    @SerializedName("provider_id")
    @Expose
    private Integer providerId;
    @SerializedName("user_id")
    @Expose
    private Integer userId;

    private Map<String,Integer> ratingData;

    private String recommended;

    public ReviewProvider(){}

    public ReviewProvider(Integer providerId, Integer userId){
        this.providerId = providerId;
        this.userId = userId;
    }

    public void setRatingData(Map<String,Integer>  ratingData) {
        this.ratingData = ratingData;
    }

    public String getReviewWaitTime() {
        return reviewWaitTime;
    }

    public void setReviewWaitTime(String reviewWaitTime) {
        this.reviewWaitTime = reviewWaitTime;
    }

    public String getReviewTitle() {
        return reviewTitle;
    }

    public void setReviewTitle(String reviewTitle) {
        this.reviewTitle = reviewTitle;
    }

    public String getReviewDescription() {
        return reviewDescription;
    }

    public void setReviewDescription(String reviewDescription) {
        this.reviewDescription = reviewDescription;
    }

    public Integer getProviderId() {
        return providerId;
    }

    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setRecommended(String recommended) {
        this.recommended = recommended;
    }
}
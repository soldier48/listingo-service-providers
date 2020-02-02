package com.tregix.serviceprovider.Model.Provider;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Gohar Ali on 2/10/2018.
 */

public class ReviewData implements Serializable{

    @SerializedName("sp_average_rating")
    @Expose
    private Double spAverageRating;
    @SerializedName("sp_total_rating")
    @Expose
    private Integer spTotalRating;
    @SerializedName("sp_total_percentage")
    @Expose
    private Double spTotalPercentage;
    @SerializedName("sp_total_recommendation")
    @Expose
    private Integer spTotalRecommendation;

    public Double getSpAverageRating() {
        return spAverageRating;
    }

    public void setSpAverageRating(Double spAverageRating) {
        this.spAverageRating = spAverageRating;
    }

    public Integer getSpTotalRating() {
        return spTotalRating;
    }

    public void setSpTotalRating(Integer spTotalRating) {
        this.spTotalRating = spTotalRating;
    }

    public int getSpTotalPercentage() {
        if(spTotalPercentage != null) {
            return spTotalPercentage.intValue();
        }else{
            return 0;
        }
    }

    public void setSpTotalPercentage(Double spTotalPercentage) {
        this.spTotalPercentage = spTotalPercentage;
    }

    public Integer getSpTotalRecommendation() {
        return spTotalRecommendation;
    }

    public void setSpTotalRecommendation(Integer spTotalRecommendation) {
        this.spTotalRecommendation = spTotalRecommendation;
    }

}
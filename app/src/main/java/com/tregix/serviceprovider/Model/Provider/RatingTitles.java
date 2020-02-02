
package com.tregix.serviceprovider.Model.Provider;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tregix.serviceprovider.Model.RatingData;

import java.io.Serializable;
import java.util.List;

public class RatingTitles implements Serializable{

    @SerializedName("leave_rating")
    @Expose
    private List<RatingData> leaveRating = null;
    @SerializedName("total_wait_time")
    @Expose
    private List<String> totalWaitTime = null;

    public List<RatingData> getLeaveRating() {
        return leaveRating;
    }

    public void setLeaveRating(List<RatingData> leaveRating) {
        this.leaveRating = leaveRating;
    }

    public List<String> getTotalWaitTime() {
        return totalWaitTime;
    }

    public void setTotalWaitTime(List<String> totalWaitTime) {
        this.totalWaitTime = totalWaitTime;
    }

}

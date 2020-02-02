
package com.tregix.serviceprovider.Model.Provider;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Saturday implements Serializable {


    @SerializedName("starttime")
    @Expose
    private List<String> starttime = null;
    @SerializedName("endtime")
    @Expose
    private List<String> endtime = null;
    @SerializedName("off_day")
    @Expose
    private String offDay;
    public String getOffDay() {
        return offDay;
    }

    public void setOffDay(String offDay) {
        this.offDay = offDay;
    }

    public List<String> getStarttime() {
        return starttime;
    }

    public void setStarttime(List<String> starttime) {
        this.starttime = starttime;
    }

    public List<String> getEndtime() {
        return endtime;
    }

    public void setEndtime(List<String> endtime) {
        this.endtime = endtime;
    }

}

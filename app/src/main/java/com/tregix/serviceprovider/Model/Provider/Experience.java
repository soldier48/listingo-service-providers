
package com.tregix.serviceprovider.Model.Provider;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Experience implements Serializable {

    @SerializedName("current")
    @Expose
    private String current;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("company")
    @Expose
    private String company;
    @SerializedName("start_date")
    @Expose
    private Integer startDate;
    @SerializedName("end_date")
    @Expose
    private Object endDate;
    @SerializedName("description")
    @Expose
    private String description;

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Integer getStartDate() {
        return startDate;
    }

    public void setStartDate(Integer startDate) {
        this.startDate = startDate;
    }

    public Object getEndDate() {
        return endDate;
    }

    public void setEndDate(Integer endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}

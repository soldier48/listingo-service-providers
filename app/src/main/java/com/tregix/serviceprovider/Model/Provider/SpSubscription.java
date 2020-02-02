
package com.tregix.serviceprovider.Model.Provider;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SpSubscription implements Serializable {
    @SerializedName("subscription_id")
    @Expose
    private Integer subscriptionId;
    @SerializedName("subscription_expiry")
    @Expose
    private Integer subscriptionExpiry;
    @SerializedName("subscription_featured_expiry")
    @Expose
    private Integer subscriptionFeaturedExpiry;
    @SerializedName("subscription_jobs")
    @Expose
    private Integer subscriptionJobs;
    @SerializedName("subscription_appointments")
    @Expose
    private String subscriptionAppointments;
    @SerializedName("subscription_profile_banner")
    @Expose
    private String subscriptionProfileBanner;
    @SerializedName("subscription_insurance")
    @Expose
    private String subscriptionInsurance;
    @SerializedName("subscription_favorites")
    @Expose
    private String subscriptionFavorites;
    @SerializedName("subscription_teams")
    @Expose
    private String subscriptionTeams;
    @SerializedName("subscription_business_hours")
    @Expose
    private String subscriptionBusinessHours;
    @SerializedName("subscription_articles")
    @Expose
    private String subscriptionArticles;

    public Integer getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(Integer subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public Integer getSubscriptionExpiry() {
        return subscriptionExpiry;
    }

    public void setSubscriptionExpiry(Integer subscriptionExpiry) {
        this.subscriptionExpiry = subscriptionExpiry;
    }

    public Integer getSubscriptionFeaturedExpiry() {
        return subscriptionFeaturedExpiry;
    }

    public void setSubscriptionFeaturedExpiry(Integer subscriptionFeaturedExpiry) {
        this.subscriptionFeaturedExpiry = subscriptionFeaturedExpiry;
    }

    public Integer getSubscriptionJobs() {
        return subscriptionJobs;
    }

    public void setSubscriptionJobs(Integer subscriptionJobs) {
        this.subscriptionJobs = subscriptionJobs;
    }

    public String getSubscriptionAppointments() {
        return subscriptionAppointments;
    }

    public void setSubscriptionAppointments(String subscriptionAppointments) {
        this.subscriptionAppointments = subscriptionAppointments;
    }

    public String getSubscriptionProfileBanner() {
        return subscriptionProfileBanner;
    }

    public void setSubscriptionProfileBanner(String subscriptionProfileBanner) {
        this.subscriptionProfileBanner = subscriptionProfileBanner;
    }

    public String getSubscriptionInsurance() {
        return subscriptionInsurance;
    }

    public void setSubscriptionInsurance(String subscriptionInsurance) {
        this.subscriptionInsurance = subscriptionInsurance;
    }

    public String getSubscriptionFavorites() {
        return subscriptionFavorites;
    }

    public void setSubscriptionFavorites(String subscriptionFavorites) {
        this.subscriptionFavorites = subscriptionFavorites;
    }

    public String getSubscriptionTeams() {
        return subscriptionTeams;
    }

    public void setSubscriptionTeams(String subscriptionTeams) {
        this.subscriptionTeams = subscriptionTeams;
    }

    public String getSubscriptionBusinessHours() {
        return subscriptionBusinessHours;
    }

    public void setSubscriptionBusinessHours(String subscriptionBusinessHours) {
        this.subscriptionBusinessHours = subscriptionBusinessHours;
    }

    public String getSubscriptionArticles() {
        return subscriptionArticles;
    }

    public void setSubscriptionArticles(String subscriptionArticles) {
        this.subscriptionArticles = subscriptionArticles;
    }

}

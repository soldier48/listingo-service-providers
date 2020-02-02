
package com.tregix.serviceprovider.Model.Provider;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Privacy implements Serializable {

    @SerializedName("profile_photo")
    @Expose
    private String profilePhoto;
    @SerializedName("profile_banner")
    @Expose
    private String profileBanner;
    @SerializedName("profile_appointment")
    @Expose
    private String profileAppointment;
    @SerializedName("profile_contact")
    @Expose
    private String profileContact;
    @SerializedName("profile_hours")
    @Expose
    private String profileHours;
    @SerializedName("profile_service")
    @Expose
    private String profileService;
    @SerializedName("profile_team")
    @Expose
    private String profileTeam;
    @SerializedName("profile_gallery")
    @Expose
    private String profileGallery;
    @SerializedName("profile_videos")
    @Expose
    private String profileVideos;

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public String getProfileBanner() {
        return profileBanner;
    }

    public void setProfileBanner(String profileBanner) {
        this.profileBanner = profileBanner;
    }

    public String getProfileAppointment() {
        return profileAppointment;
    }

    public void setProfileAppointment(String profileAppointment) {
        this.profileAppointment = profileAppointment;
    }

    public String getProfileContact() {
        return profileContact;
    }

    public void setProfileContact(String profileContact) {
        this.profileContact = profileContact;
    }

    public String getProfileHours() {
        return profileHours;
    }

    public void setProfileHours(String profileHours) {
        this.profileHours = profileHours;
    }

    public String getProfileService() {
        return profileService;
    }

    public void setProfileService(String profileService) {
        this.profileService = profileService;
    }

    public String getProfileTeam() {
        return profileTeam;
    }

    public void setProfileTeam(String profileTeam) {
        this.profileTeam = profileTeam;
    }

    public String getProfileGallery() {
        return profileGallery;
    }

    public void setProfileGallery(String profileGallery) {
        this.profileGallery = profileGallery;
    }

    public String getProfileVideos() {
        return profileVideos;
    }

    public void setProfileVideos(String profileVideos) {
        this.profileVideos = profileVideos;
    }

}

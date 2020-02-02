
package com.tregix.serviceprovider.Model.Provider;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tregix.serviceprovider.Utils.Constants;

import java.io.Serializable;

public class PrivacySettings implements Serializable {
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
    @SerializedName("privacy_introduction")
    @Expose
    private String privacyIntroduction;
    @SerializedName("privacy_languages")
    @Expose
    private String privacyLanguages;
    @SerializedName("privacy_experience")
    @Expose
    private String privacyExperience;
    @SerializedName("privacy_awards")
    @Expose
    private String privacyAwards;
    @SerializedName("privacy_qualification")
    @Expose
    private String privacyQualification;
    @SerializedName("privacy_amenity")
    @Expose
    private String privacyAmenity;
    @SerializedName("privacy_insurance")
    @Expose
    private String privacyInsurance;
    @SerializedName("privacy_brochures")
    @Expose
    private String privacyBrochures;
    @SerializedName("privacy_job_openings")
    @Expose
    private String privacyJobOpenings;
    @SerializedName("privacy_articles")
    @Expose
    private String privacyArticles;
    @SerializedName("privacy_share")
    @Expose
    private String privacyShare;

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

    public String getPrivacyIntroduction() {
        return privacyIntroduction;
    }

    public void setPrivacyIntroduction(String privacyIntroduction) {
        this.privacyIntroduction = privacyIntroduction;
    }

    public String getPrivacyLanguages() {
        return privacyLanguages;
    }

    public void setPrivacyLanguages(String privacyLanguages) {
        this.privacyLanguages = privacyLanguages;
    }

    public String getPrivacyExperience() {
        return privacyExperience;
    }

    public void setPrivacyExperience(String privacyExperience) {
        this.privacyExperience = privacyExperience;
    }

    public String getPrivacyAwards() {
        return privacyAwards;
    }

    public void setPrivacyAwards(String privacyAwards) {
        this.privacyAwards = privacyAwards;
    }

    public String getPrivacyQualification() {
        return privacyQualification;
    }

    public void setPrivacyQualification(String privacyQualification) {
        this.privacyQualification = privacyQualification;
    }

    public String getPrivacyAmenity() {
        return privacyAmenity;
    }

    public void setPrivacyAmenity(String privacyAmenity) {
        this.privacyAmenity = privacyAmenity;
    }

    public String getPrivacyInsurance() {
        return privacyInsurance;
    }

    public void setPrivacyInsurance(String privacyInsurance) {
        this.privacyInsurance = privacyInsurance;
    }

    public String getPrivacyBrochures() {
        return privacyBrochures;
    }

    public void setPrivacyBrochures(String privacyBrochures) {
        this.privacyBrochures = privacyBrochures;
    }

    public String getPrivacyJobOpenings() {
        return privacyJobOpenings;
    }

    public void setPrivacyJobOpenings(String privacyJobOpenings) {
        this.privacyJobOpenings = privacyJobOpenings;
    }

    public String getPrivacyArticles() {
        return privacyArticles;
    }

    public void setPrivacyArticles(String privacyArticles) {
        this.privacyArticles = privacyArticles;
    }

    public String getPrivacyShare() {
        return privacyShare;
    }

    public void setPrivacyShare(String privacyShare) {
        this.privacyShare = privacyShare;
    }


}

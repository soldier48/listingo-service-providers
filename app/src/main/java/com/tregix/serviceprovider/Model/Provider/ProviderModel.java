
package com.tregix.serviceprovider.Model.Provider;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tregix.serviceprovider.Model.ImageUpload.ImageUploadResponse;

import java.io.Serializable;
import java.util.List;

public class ProviderModel implements Serializable {

    @SerializedName("category_id")
    @Expose
    private String categoryId;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("ID")
    @Expose
    private Integer iD;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("website")
    @Expose
    private String website;
    @SerializedName("avatar")
    @Expose
    private String avatar;
   /* @SerializedName("wp_capabilities")
    @Expose
    private String wpCapabilities;*/
 /*   @SerializedName("wp_user_level")
    @Expose
    private String wpUserLevel;*/
    @SerializedName("usertype")
    @Expose
    private String usertype;
    @SerializedName("full_name")
    @Expose
    private String fullName;
    @SerializedName("nickname")
    @Expose
    private String nickname;
    /*@SerializedName("r_id")
    @Expose
    private String rId;*/
    @SerializedName("sub_category")
    @Expose
    private List<String> subCategory;
    @SerializedName("company_name")
    @Expose
    private String companyName;
    /*@SerializedName("profile_avatar")
    @Expose*/
    private ProfileAvatar profileAvatar;
    @SerializedName("profile_gallery_photos")
    @Expose
    private ProfileGalleryPhotos profileGalleryPhotos;
    @SerializedName("facebook")
    @Expose
    private String facebook;
    @SerializedName("twitter ")
    @Expose
    private String twitter;
    @SerializedName("linkedin")
    @Expose
    private String linkedin;
    @SerializedName("pinterest")
    @Expose
    private String pinterest;
    @SerializedName("google_plus")
    @Expose
    private String googlePlus;
    @SerializedName("tumblr")
    @Expose
    private String tumblr;
    @SerializedName("instagram")
    @Expose
    private String instagram;
    @SerializedName("skype")
    @Expose
    private String skype;
   /* @SerializedName("activation_status")
    @Expose
    private String activationStatus;*/
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("tag_line")
    @Expose
    private String tagLine;
    @SerializedName("fax")
    @Expose
    private String fax;
    @SerializedName("profile_languages")
    @Expose
    private List<String> profileLanguages = null;
    @SerializedName("zip")
    @Expose
    private String zip;
    @SerializedName("verify_user")
    @Expose
    private String verifyUser;
    /*@SerializedName("privacy")
    @Expose
    private Privacy privacy;*/
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("professional_statements")
    @Expose
    private String professionalStatements;
/*    @SerializedName("subscription_featured_expiry")
    @Expose
    private String subscriptionFeaturedExpiry;
    @SerializedName("subscription_expiry")
    @Expose
    private String subscriptionExpiry;
    @SerializedName("subscription_id")
    @Expose
    private String subscriptionId;
    @SerializedName("sp_subscription")
    @Expose
    private SpSubscription spSubscription;*/
    @SerializedName("awards")
    @Expose
    private List<Award> awards = null;
    @SerializedName("business_hours")
    @Expose
    private BusinessHours businessHours;
    @SerializedName("business_hours_format")
    @Expose
    private String businessHoursFormat;
   /* @SerializedName("default_slots")
    @Expose
    private Object defaultSlots;*/
    @SerializedName("experience")
    @Expose
    private List<Experience> experience = null;
    /*@SerializedName("profile_brochure")
    @Expose
    private String profileBrochure;*/
    /*@SerializedName("profile_insurance")
    @Expose
    private List<String> profileInsurance = null;*/
    @SerializedName("profile_amenities")
    @Expose
    private List<ProfileAmenity> profileAmenities = null;
/*    @SerializedName("audio_video_urls")
    @Expose
    private List<String> audioVideoUrls = null;*/
    @SerializedName("qualification")
    @Expose
    private List<Qualification> qualification = null;
    @SerializedName("privacy")
    @Expose
    private PrivacySettings privacySettings;
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
 /*   @SerializedName("profile_team")
    @Expose
    private String profileTeam;*/
    @SerializedName("profile_gallery")
    @Expose
    private String profileGallery;
    @SerializedName("profile_videos")
    @Expose
    private String profileVideos;
    @SerializedName("appt_booking_approved")
    @Expose
    private String apptBookingApproved;
    @SerializedName("appt_booking_confirmed")
    @Expose
    private String apptBookingConfirmed;
    @SerializedName("appt_booking_cancelled")
    @Expose
    private String apptBookingCancelled;
    @SerializedName("appt_cancelled_title")
    @Expose
    private String apptCancelledTitle;
    @SerializedName("appt_approved_title")
    @Expose
    private String apptApprovedTitle;
    @SerializedName("appt_confirmation_title")
    @Expose
    private String apptConfirmationTitle;
    @SerializedName("appointment_currency")
    @Expose
    private String appointmentCurrency;
    @SerializedName("appointment_inst_desc")
    @Expose
    private String appointmentInstDesc;
    @SerializedName("appointment_inst_title")
    @Expose
    private String appointmentInstTitle;
    @SerializedName("profile_services")
    @Expose
    private List<ProfileServices> profileServices = null;
    @SerializedName("appointment_reasons")
    @Expose
    private List<String> appointmentReasons;
    @SerializedName("appointment_types")
    @Expose
    private List<String> appointmentTypes;
  /*  @SerializedName("teams_data")
    @Expose
    private List<Integer> teamsData = null;*/
    @SerializedName("review_data")
    @Expose
    private ReviewData reviewData;

    @SerializedName("rating_titles")
    @Expose
    private RatingTitles ratingTitles;

    private ImageUploadResponse avatarObject;
    private ImageUploadResponse bannerObject;

    private boolean isfav;

    private String banner;

    public RatingTitles getRatingTitles() {
        return ratingTitles;
    }

   
     public void setRatingTitles(RatingTitles ratingTitles) {
         this.ratingTitles = ratingTitles;
     }
 

    public ReviewData getReviewData() {
        return reviewData;
    }

   
     public void setReviewData(ReviewData reviewData) {
         this.reviewData = reviewData;
     }
 

     public String getCategoryId() {
         return categoryId;
     }
 

   
     public void setCategoryId(String categoryId) {
         this.categoryId = categoryId;
     }


    public String getCategory() {
        return category;
    }

   
     public void setCategory(String category) {
         this.category = category;
     }
 

    public Integer getID() {
        return iD;
    }

   
     public void setID(Integer iD) {
         this.iD = iD;
     }
 

    public String getLatitude() {
        return latitude;
    }

   
     public void setLatitude(String latitude) {
         this.latitude = latitude;
     }
 

    public String getLongitude() {
        return longitude;
    }

   
     public void setLongitude(String longitude) {
         this.longitude = longitude;
     }
 

    public String getPhone() {
        return phone;
    }

   
     public void setPhone(String phone) {
         this.phone = phone;
     }
 

    public String getUsername() {
        return username;
    }

   
     public void setUsername(String username) {
         this.username = username;
     }
 

    public String getAvatar() {
        return avatar;
    }

   
     public void setAvatar(String avatar) {
         this.avatar = avatar;
     }
 

   /*
     public String getWpCapabilities() {
         return wpCapabilities;
     }
 

   
     public void setWpCapabilities(String wpCapabilities) {
         this.wpCapabilities = wpCapabilities;
     }
 

   
     public String getWpUserLevel() {
         return wpUserLevel;
     }
 

   
     public void setWpUserLevel(String wpUserLevel) {
         this.wpUserLevel = wpUserLevel;
     }
 */

   
     public String getUsertype() {
         return usertype;
     }
 

   
     public void setUsertype(String usertype) {
         this.usertype = usertype;
     }
 

   
     public String getFullName() {
         return fullName;
     }
 

   
     public void setFullName(String fullName) {
         this.fullName = fullName;
     }
 

   
     public String getNickname() {
         return nickname;
     }
 

   
     public void setNickname(String nickname) {
         this.nickname = nickname;
     }
 

   
    /* public String getRId() {
         return rId;
     }
 

   
     public void setRId(String rId) {
         this.rId = rId;
     }*/
 

    public List<String> getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(List<String> subCategory) {
        this.subCategory = subCategory;
    }

   
     public String getCompanyName() {
         return companyName;
     }
 

   
     public void setCompanyName(String companyName) {
         this.companyName = companyName;
     }
 

   
     public ProfileAvatar getProfileAvatar() {
         return profileAvatar;
     }
 

   
     public void setProfileAvatar(ProfileAvatar profileAvatar) {
         this.profileAvatar = profileAvatar;
     }
 

   
     public ProfileGalleryPhotos getProfileGalleryPhotos() {
         return profileGalleryPhotos;
     }
 

   
     public void setProfileGalleryPhotos(ProfileGalleryPhotos profileGalleryPhotos) {
         this.profileGalleryPhotos = profileGalleryPhotos;
     }
 

    public List<Award> getAwards() {
        return awards;
    }

   
     public void setAwards(List<Award> awards) {
         this.awards = awards;
     }
 

   
     public String getFacebook() {
         return facebook;
     }
 

   
     public void setFacebook(String facebook) {
         this.facebook = facebook;
     }
 

   
     public String getTwitter() {
         return twitter;
     }
 

   
     public void setTwitter(String twitter) {
         this.twitter = twitter;
     }
 

   
     public String getLinkedin() {
         return linkedin;
     }
 

   
     public void setLinkedin(String linkedin) {
         this.linkedin = linkedin;
     }
 

   
     public String getPinterest() {
         return pinterest;
     }
 

   
     public void setPinterest(String pinterest) {
         this.pinterest = pinterest;
     }
 

   
     public String getGooglePlus() {
         return googlePlus;
     }
 

   
     public void setGooglePlus(String googlePlus) {
         this.googlePlus = googlePlus;
     }
 

   
     public String getTumblr() {
         return tumblr;
     }
 

   
     public void setTumblr(String tumblr) {
         this.tumblr = tumblr;
     }
 

   
     public String getInstagram() {
         return instagram;
     }
 

   
     public void setInstagram(String instagram) {
         this.instagram = instagram;
     }
 

   
     public String getSkype() {
         return skype;
     }
 

   
     public void setSkype(String skype) {
         this.skype = skype;
     }
 

   
  /*   public String getActivationStatus() {
         return activationStatus;
     }
 

   
     public void setActivationStatus(String activationStatus) {
         this.activationStatus = activationStatus;
     }*/
 

    public String getAddress() {
        return address;
    }

   
     public void setAddress(String address) {
         this.address = address;
     }
 

   
    public String getTagLine() {
        return tagLine;
    }
 

   
    public void setTagLine(String tagLine) {
        this.tagLine = tagLine;
    }
 

    public String getFax() {
        return fax;
    }

   
     public void setFax(String fax) {
         this.fax = fax;
     }
 

    public List<String> getProfileLanguages() {
        return profileLanguages;
    }

   
     public void setProfileLanguages(List<String> profileLanguages) {
         this.profileLanguages = profileLanguages;
     }
 

   
    public String getZip() {
        return zip;
    }
 

   
    public void setZip(String zip) {
        this.zip = zip;
    }
 

   
     public String getVerifyUser() {
         return verifyUser;
     }
 

   
     public void setVerifyUser(String verifyUser) {
         this.verifyUser = verifyUser;
     }
 

   
   /*  public Privacy getPrivacy() {
         return privacy;
     }
 

   
     public void setPrivacy(Privacy privacy) {
         this.privacy = privacy;
     }
 */

    public String getCountry() {
      return country;
   }
 

   
    public void setCountry(String country) {
        this.country = country;
    }
 

   
    public String getCity() {
        return city;
    }
 

   
    public void setCity(String city) {
        this.city = city;
    }
 

   
    public String getDescription() {
        return description;
    }
 

   
    public void setDescription(String description) {
        this.description = description;
    }
 

   
    public String getFirstName() {
        return firstName;
    }
 

   
    public void setFirstName(String firstName) {
       this.firstName = firstName;
    }
 

   
    public String getLastName() {
        return lastName;
    }
 

   
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
 

    public String getProfessionalStatements() {
        return professionalStatements;
    }

   
     public void setProfessionalStatements(String professionalStatements) {
         this.professionalStatements = professionalStatements;
     }
 

   /*
     public String getSubscriptionFeaturedExpiry() {
         return subscriptionFeaturedExpiry;
     }
 

   
     public void setSubscriptionFeaturedExpiry(String subscriptionFeaturedExpiry) {
         this.subscriptionFeaturedExpiry = subscriptionFeaturedExpiry;
     }*/
 

    public boolean isfav() {
        return isfav;
    }

    public void setIsfav(boolean isfav) {
        this.isfav = isfav;
    }

   
   /*  public String getSubscriptionExpiry() {
         return subscriptionExpiry;
     }
 

   
     public void setSubscriptionExpiry(String subscriptionExpiry) {
         this.subscriptionExpiry = subscriptionExpiry;
     }
 

   
     public String getSubscriptionId() {
         return subscriptionId;
     }
 

   
     public void setSubscriptionId(String subscriptionId) {
         this.subscriptionId = subscriptionId;
     }
 

   
     public SpSubscription getSpSubscription() {
         return spSubscription;
     }
 

   
     public void setSpSubscription(SpSubscription spSubscription) {
         this.spSubscription = spSubscription;
     }
 */

    public BusinessHours getBusinessHours() {
        return businessHours;
    }

   
     public void setBusinessHours(BusinessHours businessHours) {
         this.businessHours = businessHours;
     }
 

    public String getBusinessHoursFormat() {
        return businessHoursFormat;
    }

   
     public void setBusinessHoursFormat(String businessHoursFormat) {
         this.businessHoursFormat = businessHoursFormat;
     }
 

   
   /*  public Object getDefaultSlots() {
         return defaultSlots;
     }
 

   
     public void setDefaultSlots(Object defaultSlots) {
         this.defaultSlots = defaultSlots;
     }*/
 

    public List<Experience> getExperience() {
        return experience;
    }

   
     public void setExperience(List<Experience> experience) {
         this.experience = experience;
     }
 

   /*
     public String getProfileBrochure() {
         return profileBrochure;
     }
 

   
     public void setProfileBrochure(String profileBrochure) {
         this.profileBrochure = profileBrochure;
     }
 */

   
    /* public List<String> getProfileInsurance() {
         return profileInsurance;
     }
 

   
     public void setProfileInsurance(List<String> profileInsurance) {
         this.profileInsurance = profileInsurance;
     }
 */

    public List<ProfileAmenity> getProfileAmenities() {
        return profileAmenities;
    }

   
     public void setProfileAmenities(List<ProfileAmenity> profileAmenities) {
         this.profileAmenities = profileAmenities;
     }
 

   
   /*  public List<String> getAudioVideoUrls() {
         return audioVideoUrls;
     }
 

   
     public void setAudioVideoUrls(List<String> audioVideoUrls) {
         this.audioVideoUrls = audioVideoUrls;
     }
 */

    public List<Qualification> getQualification() {
        return qualification;
    }

   
     public void setQualification(List<Qualification> qualification) {
         this.qualification = qualification;
     }
 

   
     public PrivacySettings getPrivacySettings() {
         return privacySettings;
     }
 

   
     public void setPrivacySettings(PrivacySettings privacySettings) {
         this.privacySettings = privacySettings;
     }
 

   
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
 

   
     /*public String getProfileTeam() {
         return profileTeam;
     }
 

   
     public void setProfileTeam(String profileTeam) {
         this.profileTeam = profileTeam;
     }*/
 

   
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
 

   
     public String getApptBookingApproved() {
         return apptBookingApproved;
     }
 

   
     public void setApptBookingApproved(String apptBookingApproved) {
         this.apptBookingApproved = apptBookingApproved;
     }
 

   
     public String getApptBookingConfirmed() {
         return apptBookingConfirmed;
     }
 

   
     public void setApptBookingConfirmed(String apptBookingConfirmed) {
         this.apptBookingConfirmed = apptBookingConfirmed;
     }
 

   
     public String getApptBookingCancelled() {
         return apptBookingCancelled;
     }
 

   
     public void setApptBookingCancelled(String apptBookingCancelled) {
         this.apptBookingCancelled = apptBookingCancelled;
     }
 

   
     public String getApptCancelledTitle() {
         return apptCancelledTitle;
     }
 

   
     public void setApptCancelledTitle(String apptCancelledTitle) {
         this.apptCancelledTitle = apptCancelledTitle;
     }
 

   
     public String getApptApprovedTitle() {
         return apptApprovedTitle;
     }
 

   
     public void setApptApprovedTitle(String apptApprovedTitle) {
         this.apptApprovedTitle = apptApprovedTitle;
     }
 

   
     public String getApptConfirmationTitle() {
         return apptConfirmationTitle;
     }
 

   
     public void setApptConfirmationTitle(String apptConfirmationTitle) {
         this.apptConfirmationTitle = apptConfirmationTitle;
     }
 

    public String getAppointmentCurrency() {
        return appointmentCurrency;
    }

   
     public void setAppointmentCurrency(String appointmentCurrency) {
         this.appointmentCurrency = appointmentCurrency;
     }
 

   
     public String getAppointmentInstDesc() {
         return appointmentInstDesc;
     }
 

   
     public void setAppointmentInstDesc(String appointmentInstDesc) {
         this.appointmentInstDesc = appointmentInstDesc;
     }
 

   
     public String getAppointmentInstTitle() {
         return appointmentInstTitle;
     }
 

   
     public void setAppointmentInstTitle(String appointmentInstTitle) {
         this.appointmentInstTitle = appointmentInstTitle;
     }
 


    public List<ProfileServices> getProfileServices() {
        return profileServices;
    }

   
     public void setProfileServices(List<ProfileServices> profileServices) {
         this.profileServices = profileServices;
     }
 

    public List<String> getAppointmentReasons() {
        return appointmentReasons;
    }

   
     public void setAppointmentReasons(List<String> appointmentReasons) {
         this.appointmentReasons = appointmentReasons;
     }
 

    public List<String> getAppointmentTypes() {
        return appointmentTypes;
    }

   
     public void setAppointmentTypes(List<String> appointmentTypes) {
         this.appointmentTypes = appointmentTypes;
     }
 

   
   /*  public List<Integer> getTeamsData() {
         return teamsData;
     }
 

   
     public void setTeamsData(List<Integer> teamsData) {
         this.teamsData = teamsData;
     }*/
 

    public String getEmail() {
        return email;
    }

   
     public void setEmail(String email) {
         this.email = email;
     }
 

    public String getWebsite() {
        return website;
    }

   
    public void setWebsite(String website) {
        this.website = website;
    }


    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public ImageUploadResponse getAvatarObject() {
        return avatarObject;
    }

    public void setAvatarObject(ImageUploadResponse avatarObject) {
        this.avatarObject = avatarObject;
    }

    public ImageUploadResponse getBannerObject() {
        return bannerObject;
    }

    public void setBannerObject(ImageUploadResponse bannerObject) {
        this.bannerObject = bannerObject;
    }
}

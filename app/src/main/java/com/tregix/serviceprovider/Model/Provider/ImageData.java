package com.tregix.serviceprovider.Model.Provider;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Gohar Ali on 12/28/2017.
 */

public class ImageData implements Serializable{


    @SerializedName("full")
    @Expose
    private String full;
    @SerializedName("thumb")
    @Expose
    private String thumb;
    @SerializedName("banner")
    @Expose
    private String banner;
    @SerializedName("image_id")
    @Expose
    private String imageId;

    public String getFull() {
        return full;
    }

    public void setFull(String full) {
        this.full = full;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

}

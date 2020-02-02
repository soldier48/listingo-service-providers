
package com.tregix.serviceprovider.Model.Provider;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ProfileAvatar implements Serializable {

    @SerializedName("image_type")
    @Expose
    private String imageType;
    @SerializedName("default_image")
    @Expose
    private String defaultImage;
    @SerializedName("image_data")
    @Expose
    private Object imageData = null;

    public Object getImageData() {
        return imageData;
    }

    public void setImageData(Object imageData) {
        this.imageData = imageData;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public String getDefaultImage() {
        return defaultImage;
    }

    public void setDefaultImage(String defaultImage) {
        this.defaultImage = defaultImage;
    }

}

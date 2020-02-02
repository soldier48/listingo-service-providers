
package com.tregix.serviceprovider.Model.ImageUpload;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Attributes_ {

    @SerializedName("embeddable")
    @Expose
    private Boolean embeddable;

    public Boolean getEmbeddable() {
        return embeddable;
    }

    public void setEmbeddable(Boolean embeddable) {
        this.embeddable = embeddable;
    }

}

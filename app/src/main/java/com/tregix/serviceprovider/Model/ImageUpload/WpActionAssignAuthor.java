
package com.tregix.serviceprovider.Model.ImageUpload;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WpActionAssignAuthor {

    @SerializedName("attributes")
    @Expose
    private List<Object> attributes = null;
    @SerializedName("href")
    @Expose
    private String href;

    public List<Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Object> attributes) {
        this.attributes = attributes;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

}

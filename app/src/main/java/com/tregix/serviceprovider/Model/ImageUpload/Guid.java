
package com.tregix.serviceprovider.Model.ImageUpload;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Guid {

    @SerializedName("rendered")
    @Expose
    private String rendered;
    @SerializedName("raw")
    @Expose
    private String raw;

    public String getRendered() {
        return rendered;
    }

    public void setRendered(String rendered) {
        this.rendered = rendered;
    }

    public String getRaw() {
        return raw;
    }

    public void setRaw(String raw) {
        this.raw = raw;
    }

}

package com.tregix.serviceprovider.Model.categories;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by Gohar Ali on 2/6/2018.
 */

public class CategoryImage extends RealmObject {

    @SerializedName("attachment_id")
    @Expose
    private String attachmentId;
    @SerializedName("url")
    @Expose
    private String url;

    public String getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(String attachmentId) {
        this.attachmentId = attachmentId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}

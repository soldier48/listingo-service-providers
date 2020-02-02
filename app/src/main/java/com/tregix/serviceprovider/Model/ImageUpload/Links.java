
package com.tregix.serviceprovider.Model.ImageUpload;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Links {

    @SerializedName("self")
    @Expose
    private List<Self> self = null;
    @SerializedName("collection")
    @Expose
    private List<Collection> collection = null;
    @SerializedName("about")
    @Expose
    private List<About> about = null;
    @SerializedName("author")
    @Expose
    private List<Author> author = null;
    @SerializedName("replies")
    @Expose
    private List<Reply> replies = null;
    @SerializedName("wp:action-assign-author")
    @Expose
    private List<WpActionAssignAuthor> wpActionAssignAuthor = null;
    @SerializedName("curies")
    @Expose
    private List<Cury> curies = null;

    public List<Self> getSelf() {
        return self;
    }

    public void setSelf(List<Self> self) {
        this.self = self;
    }

    public List<Collection> getCollection() {
        return collection;
    }

    public void setCollection(List<Collection> collection) {
        this.collection = collection;
    }

    public List<About> getAbout() {
        return about;
    }

    public void setAbout(List<About> about) {
        this.about = about;
    }

    public List<Author> getAuthor() {
        return author;
    }

    public void setAuthor(List<Author> author) {
        this.author = author;
    }

    public List<Reply> getReplies() {
        return replies;
    }

    public void setReplies(List<Reply> replies) {
        this.replies = replies;
    }

    public List<WpActionAssignAuthor> getWpActionAssignAuthor() {
        return wpActionAssignAuthor;
    }

    public void setWpActionAssignAuthor(List<WpActionAssignAuthor> wpActionAssignAuthor) {
        this.wpActionAssignAuthor = wpActionAssignAuthor;
    }

    public List<Cury> getCuries() {
        return curies;
    }

    public void setCuries(List<Cury> curies) {
        this.curies = curies;
    }

}

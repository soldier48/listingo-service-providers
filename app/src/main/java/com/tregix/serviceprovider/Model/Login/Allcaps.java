package com.tregix.serviceprovider.Model.Login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class Allcaps extends RealmObject {

    @SerializedName("spectate")
    @Expose
    private Boolean spectate;
    @SerializedName("participate")
    @Expose
    private Boolean participate;
    @SerializedName("read_private_forums")
    @Expose
    private Boolean readPrivateForums;
    @SerializedName("publish_topics")
    @Expose
    private Boolean publishTopics;
    @SerializedName("edit_topics")
    @Expose
    private Boolean editTopics;
    @SerializedName("publish_replies")
    @Expose
    private Boolean publishReplies;
    @SerializedName("edit_replies")
    @Expose
    private Boolean editReplies;
    @SerializedName("assign_topic_tags")
    @Expose
    private Boolean assignTopicTags;
    @SerializedName("customer")
    @Expose
    private Boolean customer;
    @SerializedName("bbp_participant")
    @Expose
    private Boolean bbpParticipant;

    public Boolean getSpectate() {
        return spectate;
    }

    public void setSpectate(Boolean spectate) {
        this.spectate = spectate;
    }

    public Boolean getParticipate() {
        return participate;
    }

    public void setParticipate(Boolean participate) {
        this.participate = participate;
    }

    public Boolean getReadPrivateForums() {
        return readPrivateForums;
    }

    public void setReadPrivateForums(Boolean readPrivateForums) {
        this.readPrivateForums = readPrivateForums;
    }

    public Boolean getPublishTopics() {
        return publishTopics;
    }

    public void setPublishTopics(Boolean publishTopics) {
        this.publishTopics = publishTopics;
    }

    public Boolean getEditTopics() {
        return editTopics;
    }

    public void setEditTopics(Boolean editTopics) {
        this.editTopics = editTopics;
    }

    public Boolean getPublishReplies() {
        return publishReplies;
    }

    public void setPublishReplies(Boolean publishReplies) {
        this.publishReplies = publishReplies;
    }

    public Boolean getEditReplies() {
        return editReplies;
    }

    public void setEditReplies(Boolean editReplies) {
        this.editReplies = editReplies;
    }

    public Boolean getAssignTopicTags() {
        return assignTopicTags;
    }

    public void setAssignTopicTags(Boolean assignTopicTags) {
        this.assignTopicTags = assignTopicTags;
    }

    public Boolean getCustomer() {
        return customer;
    }

    public void setCustomer(Boolean customer) {
        this.customer = customer;
    }

    public Boolean getBbpParticipant() {
        return bbpParticipant;
    }

    public void setBbpParticipant(Boolean bbpParticipant) {
        this.bbpParticipant = bbpParticipant;
    }

}

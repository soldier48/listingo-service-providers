package com.tregix.serviceprovider.Model.Provider;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by tregix on 3/9/2018.
 */

public class ProviderReviewListData {

        @SerializedName("ID")
        @Expose
        private Integer iD;
        @SerializedName("post_author")
        @Expose
        private String postAuthor;
        @SerializedName("reviewer_name")
        @Expose
        private String reviewerName;
        @SerializedName("review_wait_time")
        @Expose
        private String reviewWaitTime;
        @SerializedName("category_type")
        @Expose
        private String categoryType;
        @SerializedName("total_time")
        @Expose
        private List<Object> totalTime = null;
        @SerializedName("avatar")
        @Expose
        private String avatar;
        @SerializedName("author_url")
        @Expose
        private String authorUrl;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("review_date")
        @Expose
        private String reviewDate;
        @SerializedName("total_ratings")
        @Expose
        private String totalRatings;
        @SerializedName("description")
        @Expose
        private String description;

        public Integer getID() {
            return iD;
        }

        public void setID(Integer iD) {
            this.iD = iD;
        }

        public String getPostAuthor() {
            return postAuthor;
        }

        public void setPostAuthor(String postAuthor) {
            this.postAuthor = postAuthor;
        }

        public String getReviewerName() {
            return reviewerName;
        }

        public void setReviewerName(String reviewerName) {
            this.reviewerName = reviewerName;
        }

        public String getReviewWaitTime() {
            return reviewWaitTime;
        }

        public void setReviewWaitTime(String reviewWaitTime) {
            this.reviewWaitTime = reviewWaitTime;
        }

        public String getCategoryType() {
            return categoryType;
        }

        public void setCategoryType(String categoryType) {
            this.categoryType = categoryType;
        }

        public List<Object> getTotalTime() {
            return totalTime;
        }

        public void setTotalTime(List<Object> totalTime) {
            this.totalTime = totalTime;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getAuthorUrl() {
            return authorUrl;
        }

        public void setAuthorUrl(String authorUrl) {
            this.authorUrl = authorUrl;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getReviewDate() {
            return reviewDate;
        }

        public void setReviewDate(String reviewDate) {
            this.reviewDate = reviewDate;
        }

        public String getTotalRatings() {
            return totalRatings;
        }

        public void setTotalRatings(String totalRatings) {
            this.totalRatings = totalRatings;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

}

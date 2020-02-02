package com.tregix.serviceprovider.Model.Provider;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Confiz123 on 3/12/2018.
 */

public class MarkFavoriteParam {

    @SerializedName("provider_id")
    @Expose
    private Integer providerId;
    @SerializedName("user_id")
    @Expose
    private Integer userId;

    @SerializedName("isfav")
    @Expose
    private String isFav;

    public MarkFavoriteParam(Integer providerId, Integer userId,boolean fav){
        this.providerId = providerId;
        this.userId = userId;

        if(fav){
            isFav = "yes";
        }else{
            isFav = "no";
        }
    }

    public Integer getProviderId() {
        return providerId;
    }

    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}

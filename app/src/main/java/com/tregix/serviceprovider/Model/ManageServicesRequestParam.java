package com.tregix.serviceprovider.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tregix.serviceprovider.Model.Provider.ProfileServices;

import java.util.List;

/**
 * Created by Confiz123 on 3/8/2018.
 */

public class ManageServicesRequestParam {

    @SerializedName("profile_services")
    @Expose
    private List<ProfileServices> services;
    @SerializedName("user_id")
    @Expose
    private Integer userId;

    public ManageServicesRequestParam(int uid, List<ProfileServices> services){
        this.services = services;
        this.userId = uid;
    }

    public List<ProfileServices> getServices() {
        return services;
    }

    public void setServices(List<ProfileServices> services) {
        this.services = services;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}

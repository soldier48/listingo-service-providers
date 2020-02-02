package com.tregix.serviceprovider.Model.Login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

public class Data extends RealmObject {

    @SerializedName("data")
    @Expose
    private Data_ data;
    @SerializedName("ID")
    @Expose
    private Integer iD;
    @SerializedName("caps")
    @Expose
    private Caps caps;
    @SerializedName("cap_key")
    @Expose
    private String capKey;
    @SerializedName("roles")
    @Expose
    private RealmList<String> roles = null;
    @SerializedName("allcaps")
    @Expose
    private Allcaps allcaps;


    public Data_ getData() {
        return data;
    }

    public void setData(Data_ data) {
        this.data = data;
    }

    public Integer getID() {
        return iD;
    }

    public void setID(Integer iD) {
        this.iD = iD;
    }

    public Caps getCaps() {
        return caps;
    }

    public void setCaps(Caps caps) {
        this.caps = caps;
    }

    public String getCapKey() {
        return capKey;
    }

    public void setCapKey(String capKey) {
        this.capKey = capKey;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(RealmList<String> roles) {
        this.roles = roles;
    }

    public Allcaps getAllcaps() {
        return allcaps;
    }

    public void setAllcaps(Allcaps allcaps) {
        this.allcaps = allcaps;
    }



}

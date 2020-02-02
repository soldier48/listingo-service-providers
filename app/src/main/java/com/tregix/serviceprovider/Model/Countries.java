package com.tregix.serviceprovider.Model;

import com.google.gson.annotations.Expose;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by Gohar Ali on 1/3/2018.
 */

public class Countries extends RealmObject{

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("cities")
    @Expose
    private RealmList<String> cities = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getCities() {
        return cities;
    }

    public void setCities(RealmList<String> cities) {
        this.cities = cities;
    }

}

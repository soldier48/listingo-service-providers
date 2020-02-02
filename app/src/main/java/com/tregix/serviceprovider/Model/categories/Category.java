
package com.tregix.serviceprovider.Model.categories;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

public class Category extends RealmObject implements Serializable{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("sub_categories")
    @Expose
    private RealmList<SubCategory> subCategories = null;

    @SerializedName("category_image")
    @Expose
    private CategoryImage categoryImage;

    @SerializedName("no_of_provider")
    @Expose
    private Integer noOfProviders;

    public CategoryImage getCategoryImage() {
        return categoryImage;
    }

    public void setCategoryImage(CategoryImage categoryImage) {
        this.categoryImage = categoryImage;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<SubCategory> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(RealmList<SubCategory> subCategories) {
        this.subCategories = subCategories;
    }

    public Integer getNoOfProviders() {
        return noOfProviders;
    }

    public void setNoOfProviders(Integer noOfProviders) {
        this.noOfProviders = noOfProviders;
    }
}

package com.tregix.serviceprovider.Model;

import java.io.Serializable;

/**
 * Created by Confiz123 on 3/6/2018.
 */

public class RatingData implements Serializable {

    private float rating = 1.0f;
    private String title;
    private String slug;

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }


}

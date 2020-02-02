package com.tregix.serviceprovider.Model;

public class UserImageModel {

    private String id;
    private String url;
    private String name;
    private boolean isSelected;

    public UserImageModel(String id, String url, String name){
        this.id = id;
        this.url = url;
        this.name = name;
    }

    public UserImageModel(String id, String url, String name,boolean isSelected){
        this.id = id;
        this.url = url;
        this.name = name;
        this.isSelected = isSelected;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}

package com.tregix.serviceprovider.Model;

/**
 * Created by Gohar Ali on 12/25/2017.
 */

public class SelectableItem {
    private boolean isSelected = false;
    private String name;
    private String key;

    public SelectableItem(String item, boolean isSelected) {
        this.isSelected = isSelected;
        this.name = item;
    }

    public SelectableItem(String item, boolean isSelected, String key) {
        this.isSelected = isSelected;
        this.name = item;
        this.key = key;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }


}

package com.drchip.android.models;

import android.graphics.drawable.Drawable;

/**
 * Created by mohann on 22-04-2016.
 */
public class HomePageOptions {

    private String name;
    private String type;
    private Drawable iconUrl;

    public HomePageOptions() {
    }

    public HomePageOptions(String name, String type, Drawable iconUrl) {
        this.name = name;
        this.type = type;
        this.iconUrl = iconUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Drawable getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(Drawable iconUrl) {
        this.iconUrl = iconUrl;
    }

    @Override
    public String toString() {
        return "HomePageOptions{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", iconUrl='" + iconUrl.toString() + '\'' +
                '}';
    }
}

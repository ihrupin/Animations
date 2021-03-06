package com.hrupin.animations.domain;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Igor Khrupin www.hrupin.com on 9/28/16.
 */

public class DishItem {
    private String imageUrl;
    private String name;
    private String time;

    public DishItem(String name, String time, String imageUrl) {
        this.imageUrl = imageUrl;
        this.name = name;
        this.time = time;
    }

    public DishItem() {

    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

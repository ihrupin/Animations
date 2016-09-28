package com.hrupin.animations.domain;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Igor Khrupin www.hrupin.com on 9/28/16.
 */

public class DishListItem implements Parcelable {
    private String previewImageUrl;
    private String name;
    private String time;

    public String getPreviewImageUrl() {
        return previewImageUrl;
    }

    public void setPreviewImageUrl(String previewImageUrl) {
        this.previewImageUrl = previewImageUrl;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}

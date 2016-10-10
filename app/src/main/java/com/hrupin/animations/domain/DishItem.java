package com.hrupin.animations.domain;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Igor Khrupin www.hrupin.com on 9/28/16.
 */

public class DishItem implements Parcelable {
    private String previewImageUrl;
    private String imageUrl;
    private String name;
    private String time;

    protected DishItem(Parcel in) {
        previewImageUrl = in.readString();
        imageUrl = in.readString();
        name = in.readString();
        time = in.readString();
    }

    public String getPreviewImageUrl() {
        return previewImageUrl;
    }

    public void setPreviewImageUrl(String previewImageUrl) {
        this.previewImageUrl = previewImageUrl;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(previewImageUrl);
        dest.writeString(imageUrl);
        dest.writeString(name);
        dest.writeString(time);
    }

    public static final Creator<DishItem> CREATOR = new Creator<DishItem>() {
        @Override
        public DishItem createFromParcel(Parcel in) {
            return new DishItem(in);
        }

        @Override
        public DishItem[] newArray(int size) {
            return new DishItem[size];
        }
    };
}

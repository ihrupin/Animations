package com.hrupin.animations.domain;

/**
 * Created by Igor Khrupin www.hrupin.com on 9/28/16.
 */

public class DishListItem {
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
}

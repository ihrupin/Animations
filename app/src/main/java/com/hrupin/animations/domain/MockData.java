package com.hrupin.animations.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Igor Khrupin www.hrupin.com on 9/28/16.
 */

public class MockData {
    private static List<DishListItem> dishList = new ArrayList<>();

    static {
        DishListItem item = new DishListItem();
        item.setName("Mother's Salad");
        item.setTime("Sep 28, 2016. 10:00 - 11:00 AM");
        item.setPreviewImageUrl("https://unsplash.it/100/100?image=0");

        for(int i = 0; i < 100; i++){
            dishList.add(item);
        }
    }

    public static List<DishListItem> getDishList() {
        return dishList;
    }
}

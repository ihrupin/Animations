package com.hrupin.animations.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Igor Khrupin www.hrupin.com on 9/28/16.
 */

public class MockData {
    private static List<DishItem> dishList = new ArrayList<>();

    private static final DishItem item;

    static {
        item = new DishItem();
        item.setName("Laptop");
        item.setTime("Sep 28, 2016. 10:00 - 11:00 AM");
        item.setImageUrl("https://unsplash.it/500/500?image=6");

        for(int i = 0; i < 100; i++){
            dishList.add(item);
        }
    }

    public static List<DishItem> getDishList() {
        return dishList;
    }

    public static DishItem getItem() {
        return item;
    }
}

package com.example.carrotandstick.src.calendar;

import android.widget.FrameLayout;
import android.widget.ImageView;

public class Item {
    String dday;
    String goal;
    FrameLayout frameLayout;
    ImageView imageView;

    public Item(String dday, String goal) {
        this.dday = dday;
        this.goal = goal;
    }

    public String getDday() {
        return dday;
    }

    public String getGoal() {
        return goal;
    }

    public FrameLayout getFrameLayout() {
        return frameLayout;
    }

    public ImageView getImageView() {
        return imageView;
    }
}

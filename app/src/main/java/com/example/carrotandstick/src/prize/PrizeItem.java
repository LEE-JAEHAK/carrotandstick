package com.example.carrotandstick.src.prize;

import android.widget.FrameLayout;
import android.widget.ImageView;

public class PrizeItem {
    String prizeName;
    int prizeNum;
    ImageView imageView;

    public PrizeItem(String prizeName) {
        this.prizeName = prizeName;
    }

    public String getPrizeName() {
        return prizeName;
    }

    public int getPrizeNum() {
        return prizeNum;
    }

    public ImageView getImageView() {
        return imageView;
    }
}

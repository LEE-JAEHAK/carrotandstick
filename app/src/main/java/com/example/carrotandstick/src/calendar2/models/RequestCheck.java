package com.example.carrotandstick.src.calendar2.models;

import com.google.gson.annotations.SerializedName;

public class RequestCheck {
    @SerializedName("goalNo")
    int goalNo;

    public void setGoalNo(int goalNo) {
        this.goalNo = goalNo;
    }
}

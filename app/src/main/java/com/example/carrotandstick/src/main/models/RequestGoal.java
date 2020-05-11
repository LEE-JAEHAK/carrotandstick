package com.example.carrotandstick.src.main.models;

import com.google.gson.annotations.SerializedName;

public class RequestGoal {
    @SerializedName("goal")
    String goal;

    public void setGoal(String goal) {
        this.goal = goal;
    }
}

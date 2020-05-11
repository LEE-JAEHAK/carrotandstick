package com.example.carrotandstick.src.main.models;

import com.google.gson.annotations.SerializedName;

public class MainResponse {
    @SerializedName("isSuccess")
    boolean isSuccess;

    @SerializedName("code")
    int code;

    @SerializedName("message")
    String message;

    public boolean getIsSuccess() {
        return isSuccess;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

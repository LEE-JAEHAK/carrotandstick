package com.example.carrotandstick.src.calendar2.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CheckResponse {

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

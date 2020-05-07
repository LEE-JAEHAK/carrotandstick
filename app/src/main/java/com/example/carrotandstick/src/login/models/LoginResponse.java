package com.example.carrotandstick.src.login.models;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
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

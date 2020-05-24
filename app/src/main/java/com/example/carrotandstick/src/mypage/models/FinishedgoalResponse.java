package com.example.carrotandstick.src.mypage.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FinishedgoalResponse {

    @SerializedName("result")
    ArrayList<Result> result;

    @SerializedName("isSuccess")
    boolean isSuccess;

    @SerializedName("code")
    int code;

    @SerializedName("message")
    String message;

    public class Result {
        @SerializedName("goal")
        String goal;

        @SerializedName("createdAt")
        String createdAt;

        @SerializedName("isDeleted")
        String isDeleted;

        public String getGoal() {
            return goal;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public String getIsDeleted() {
            return isDeleted;
        }
    }
    public ArrayList<Result> getResult() {
        return result;
    }
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

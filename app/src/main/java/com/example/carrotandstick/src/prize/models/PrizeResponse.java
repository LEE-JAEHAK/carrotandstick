package com.example.carrotandstick.src.prize.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PrizeResponse {

    @SerializedName("result")
    ArrayList<Result> result;

    @SerializedName("isSuccess")
    boolean isSuccess;

    @SerializedName("code")
    int code;

    @SerializedName("message")
    String message;

    public class Result {
        @SerializedName("no")
        int no;

        @SerializedName("name")
        String name;

        @SerializedName("imageUrl")
        String imageUrl;

        @SerializedName("cnt")
        int cnt;

        public String getName() {
            return name;
        }

        public int getNo() {
            return no;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public int getCnt() {
            return cnt;
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

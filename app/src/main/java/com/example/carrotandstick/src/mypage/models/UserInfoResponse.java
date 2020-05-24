package com.example.carrotandstick.src.mypage.models;

import com.google.gson.annotations.SerializedName;

public class UserInfoResponse {

    @SerializedName("result")
    Result result;

    @SerializedName("isSuccess")
    boolean isSuccess;

    @SerializedName("code")
    int code;

    @SerializedName("message")
    String message;

    public Result getResult() {
        return result;
    }

    public class Result {
        @SerializedName("id")
        String id;

        @SerializedName("nickName")
        String nickName;

        @SerializedName("imgUrl")
        String imgUrl;

        public String getId() {
            return id;
        }

        public String getNickName() {
            return nickName;
        }

        public String getImgUrl() {
            return imgUrl;
        }
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

package com.example.carrotandstick.src.calendar2.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GoalNoResponse {

    @SerializedName("result")
    Result result;

    @SerializedName("isSuccess")
    boolean isSuccess;

    @SerializedName("code")
    int code;

    @SerializedName("message")
    String message;

    public class Result {
        @SerializedName("no")
        int no;

        @SerializedName("contents")
        String contents;

        @SerializedName("createdAt")
        String createdAt;

        @SerializedName("checkResult")
        ArrayList<checkResult> checkResult;

        public class checkResult {
            @SerializedName("createdAt")
            String createdAt;

            public String getCreatedAt() {
                return createdAt;
            }
        }

        public ArrayList<Result.checkResult> getCheckResult() {
            return checkResult;
        }

        public String getContents() {
            return contents;
        }

        public int getNo() {
            return no;
        }

        public String getCreatedAt() {
            return createdAt;
        }

    }

    public Result getResult() {
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

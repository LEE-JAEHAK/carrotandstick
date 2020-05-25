package com.example.carrotandstick.src.calendar.models;

import com.example.carrotandstick.src.calendar2.models.GoalNoResponse;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GoalOngoingResponse {

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

        @SerializedName("goal")
        String goal;

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

        public int getNo() {
            return no;
        }

        public String getGoal() {
            return goal;
        }

        public String getCreatedAt() {
            return createdAt;
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

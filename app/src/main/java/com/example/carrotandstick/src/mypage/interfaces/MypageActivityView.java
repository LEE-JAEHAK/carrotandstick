package com.example.carrotandstick.src.mypage.interfaces;


import com.example.carrotandstick.src.mypage.models.GoalOngoingResponse;
import com.example.carrotandstick.src.mypage.models.FinishedgoalResponse;
import com.example.carrotandstick.src.mypage.models.UserInfoResponse;

import java.util.ArrayList;

public interface MypageActivityView {
    void validateUserInfoSuccess(UserInfoResponse.Result result, boolean isSuccess, int code, String message);

    void validateUserInfoFail(String msg);

    void validateGoalOngoingSuccess(ArrayList<GoalOngoingResponse.Result> result, boolean isSuccess, int code, String message);

    void validateGoalOngoingFail(String msg);

    void validateGoalFinishedSuccess(ArrayList<FinishedgoalResponse.Result> result, boolean isSuccess, int code, String message);

    void validateGoalFinishedFail(String msg);
}

package com.example.carrotandstick.src.calendar2.interfaces;


import com.example.carrotandstick.src.calendar2.models.GoalNoResponse;

public interface Calendar2ActivityView {

    void validateGoalNoSuccess(GoalNoResponse.Result result, boolean isSuccess, int code, String message);

    void validateGoalNoFail(String msg);

    void validateCheckSuccess(boolean isSuccess, int code, String message);

    void validateCheckFail(String msg);

    void validateDeleteSuccess(boolean isSuccess, int code, String message);

    void validateDeleteFail(String msg);

    void validatePostCollectionSuccess(boolean isSuccess, int code, String message);

    void validatePostCollectionFail(String msg);
}

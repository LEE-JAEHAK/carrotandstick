package com.example.carrotandstick.src.calendar2.interfaces;


import com.example.carrotandstick.src.calendar2.models.GoalNoResponse;

public interface Calendar2ActivityView {

    void validateGoalNoSuccess(GoalNoResponse.Result result, boolean isSuccess, int code, String message);

    void validateGoalNoFail(String msg);
}

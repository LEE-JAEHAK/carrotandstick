package com.example.carrotandstick.src.calendar.interfaces;


import com.example.carrotandstick.src.calendar.models.GoalOngoingResponse;

import java.util.ArrayList;

public interface CalendarActivityView {
    void validateOngoingSuccess(ArrayList<GoalOngoingResponse.Result> result, boolean isSuccess, int code, String message);

    void validateOngoingFail(String msg);
}

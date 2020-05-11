package com.example.carrotandstick.src.calendar.interfaces;


import com.example.carrotandstick.src.calendar.models.CalendarResponse;

import java.util.ArrayList;

public interface CalendarActivityView {
    void validateUserSuccess(ArrayList<CalendarResponse.Result> result, boolean isSuccess, int code, String message);

    void validateUserFail(String msg);
}

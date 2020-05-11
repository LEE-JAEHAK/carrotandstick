package com.example.carrotandstick.src.calendar.interfaces;

import com.example.carrotandstick.src.calendar.models.CalendarResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CalendarRetrofitInterface {
    @GET("/goal/ongoing")
    Call<CalendarResponse> getGoalOngoing();
}

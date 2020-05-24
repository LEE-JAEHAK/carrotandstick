package com.example.carrotandstick.src.calendar.interfaces;

import com.example.carrotandstick.src.calendar.models.GoalOngoingResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CalendarRetrofitInterface {
    @GET("/goal/ongoing")
    Call<GoalOngoingResponse> getGoalOngoing();
}

package com.example.carrotandstick.src.calendar2.interfaces;

import com.example.carrotandstick.src.calendar2.models.GoalNoResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Calendar2RetrofitInterface {
    @GET("/goal/{goalNo}")
    Call<GoalNoResponse> getGoal();
}

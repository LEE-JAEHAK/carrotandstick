package com.example.carrotandstick.src.main.interfaces;

import com.example.carrotandstick.src.main.models.MainResponse;
import com.example.carrotandstick.src.main.models.RequestGoal;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface MainRetrofitInterface {
    @POST("/goal")
    Call<MainResponse> postGoal(@Body RequestGoal params);
}

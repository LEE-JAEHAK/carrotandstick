package com.example.carrotandstick.src.prize.interfaces;

import com.example.carrotandstick.src.calendar.models.GoalOngoingResponse;
import com.example.carrotandstick.src.prize.models.PrizeResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PrizeRetrofitInterface {
    @GET("/collection")
    Call<PrizeResponse> getPrize();
}

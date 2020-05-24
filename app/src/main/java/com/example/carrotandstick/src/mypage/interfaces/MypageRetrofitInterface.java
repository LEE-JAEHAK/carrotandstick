package com.example.carrotandstick.src.mypage.interfaces;

import com.example.carrotandstick.src.mypage.models.FinishedgoalResponse;
import com.example.carrotandstick.src.mypage.models.UserInfoResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MypageRetrofitInterface {
    @GET("/user")
    Call<UserInfoResponse> getUser();

    @GET("/goal/finished")
    Call<FinishedgoalResponse> getGoalFinished();
}
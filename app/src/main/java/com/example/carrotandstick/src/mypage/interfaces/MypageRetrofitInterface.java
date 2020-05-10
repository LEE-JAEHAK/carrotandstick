package com.example.carrotandstick.src.mypage.interfaces;

import com.example.carrotandstick.src.mypage.models.MypageResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MypageRetrofitInterface {
    @GET("/user")
    Call<MypageResponse> getUser();
}

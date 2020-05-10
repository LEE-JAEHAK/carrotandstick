package com.example.carrotandstick.src.login.interfaces;

import com.example.carrotandstick.src.login.models.LoginResponse;
import com.example.carrotandstick.src.login.models.RequestUser;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginRetrofitInterface {
    @POST("/user/token")
    Call<LoginResponse> postUser(@Body RequestUser params);
}

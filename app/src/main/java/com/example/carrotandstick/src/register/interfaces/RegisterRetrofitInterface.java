package com.example.carrotandstick.src.register.interfaces;

import com.example.carrotandstick.src.register.models.RegisterResponse;
import com.example.carrotandstick.src.register.models.RequestUser;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RegisterRetrofitInterface {
    @POST("/user")
    Call<RegisterResponse> postUser(@Body RequestUser params);
}

package com.example.carrotandstick.src.calendar2.interfaces;

import com.example.carrotandstick.src.calendar2.models.CheckResponse;
import com.example.carrotandstick.src.calendar2.models.GoalNoResponse;
import com.example.carrotandstick.src.calendar2.models.RequestCheck;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Calendar2RetrofitInterface {
    @GET("/goal/{goalNo}")
    Call<GoalNoResponse> getGoal(
            @Path("goalNo") final int goalNo
    );

    @POST("/goal/check")
    Call<CheckResponse> postCheck(@Body RequestCheck params);

    @DELETE("/goal")
    Call<CheckResponse> deleteGoal(
            @Query("goalNo") final int goalNo
    );

    @POST("/collection")
    Call<CheckResponse> postCollection(@Body RequestCheck params);
}

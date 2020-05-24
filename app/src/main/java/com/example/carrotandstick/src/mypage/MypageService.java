package com.example.carrotandstick.src.mypage;

import com.example.carrotandstick.src.calendar.interfaces.CalendarRetrofitInterface;
import com.example.carrotandstick.src.calendar.models.GoalOngoingResponse;
import com.example.carrotandstick.src.mypage.interfaces.MypageActivityView;
import com.example.carrotandstick.src.mypage.interfaces.MypageRetrofitInterface;
import com.example.carrotandstick.src.mypage.models.FinishedgoalResponse;
import com.example.carrotandstick.src.mypage.models.UserInfoResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.carrotandstick.src.ApplicationClass.getRetrofit;

public class MypageService {
    final MypageActivityView mypageActivityView;

    public MypageService(MypageActivityView mypageActivityView) {
        this.mypageActivityView = mypageActivityView;
    }

    void getUser() {
        final MypageRetrofitInterface mypageRetrofitInterface = getRetrofit().create(MypageRetrofitInterface.class);
        mypageRetrofitInterface.getUser().enqueue(new Callback<UserInfoResponse>() {
            @Override
            public void onResponse(Call<UserInfoResponse> call, Response<UserInfoResponse> response) {
                final UserInfoResponse userInfoResponse = response.body();
                if (userInfoResponse == null) {
                    mypageActivityView.validateUserInfoFail(response.message());
                    return;
                }
                mypageActivityView.validateUserInfoSuccess(userInfoResponse.getResult(), userInfoResponse.getIsSuccess(), userInfoResponse.getCode(), userInfoResponse.getMessage());
            }

            @Override
            public void onFailure(Call<UserInfoResponse> call, Throwable t) {
                mypageActivityView.validateUserInfoFail(t.getMessage());
            }
        });
    }

    public void getGoalOngoing() {
        final CalendarRetrofitInterface calendarRetrofitInterface = getRetrofit().create(CalendarRetrofitInterface.class);
        calendarRetrofitInterface.getGoalOngoing().enqueue(new Callback<GoalOngoingResponse>() {
            @Override
            public void onResponse(Call<GoalOngoingResponse> call, Response<GoalOngoingResponse> response) {
                final GoalOngoingResponse goalOngoingResponse = response.body();
                mypageActivityView.validateGoalOngoingSuccess(goalOngoingResponse.getResult(), goalOngoingResponse.getIsSuccess(), goalOngoingResponse.getCode(), goalOngoingResponse.getMessage());
            }

            @Override
            public void onFailure(Call<GoalOngoingResponse> call, Throwable t) {
                mypageActivityView.validateGoalOngoingFail(t.getMessage());
            }
        });
    }

    public void getGoalFinished(){
        final MypageRetrofitInterface mypageRetrofitInterface = getRetrofit().create((MypageRetrofitInterface.class));
        mypageRetrofitInterface.getGoalFinished().enqueue(new Callback<FinishedgoalResponse>() {
            @Override
            public void onResponse(Call<FinishedgoalResponse> call, Response<FinishedgoalResponse> response) {
                final FinishedgoalResponse finishedgoalResponse = response.body();
                mypageActivityView.validateGoalFinishedSuccess(finishedgoalResponse.getResult(), finishedgoalResponse.getIsSuccess(), finishedgoalResponse.getCode(),finishedgoalResponse.getMessage());
            }

            @Override
            public void onFailure(Call<FinishedgoalResponse> call, Throwable t) {
                mypageActivityView.validateGoalFinishedFail(t.getMessage());
            }
        });
    }
}

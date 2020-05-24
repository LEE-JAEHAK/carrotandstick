package com.example.carrotandstick.src.calendar;

import com.example.carrotandstick.src.calendar.interfaces.CalendarActivityView;
import com.example.carrotandstick.src.calendar.interfaces.CalendarRetrofitInterface;
import com.example.carrotandstick.src.calendar.models.GoalOngoingResponse;
import com.example.carrotandstick.src.calendar2.models.GoalNoResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.carrotandstick.src.ApplicationClass.getRetrofit;

public class CalendarService {
    final CalendarActivityView calendarActivityView;

    public CalendarService(CalendarActivityView calendarActivityView) {
        this.calendarActivityView = calendarActivityView;
    }

    public void getGoalOngoing() {
        final CalendarRetrofitInterface calendarRetrofitInterface = getRetrofit().create(CalendarRetrofitInterface.class);
        calendarRetrofitInterface.getGoalOngoing().enqueue(new Callback<GoalOngoingResponse>() {
            @Override
            public void onResponse(Call<GoalOngoingResponse> call, Response<GoalOngoingResponse> response) {
                final GoalOngoingResponse goalOngoingResponse = response.body();
                if (goalOngoingResponse == null) {
                    calendarActivityView.validateOngoingFail(response.message());
                    return;
                }
                calendarActivityView.validateOngoingSuccess(goalOngoingResponse.getResult(), goalOngoingResponse.getIsSuccess(), goalOngoingResponse.getCode(), goalOngoingResponse.getMessage());
            }

            @Override
            public void onFailure(Call<GoalOngoingResponse> call, Throwable t) {
                calendarActivityView.validateOngoingFail(t.getMessage());
            }
        });
    }
}

package com.example.carrotandstick.src.calendar;

import com.example.carrotandstick.src.calendar.interfaces.CalendarActivityView;
import com.example.carrotandstick.src.calendar.interfaces.CalendarRetrofitInterface;
import com.example.carrotandstick.src.calendar.models.CalendarResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.carrotandstick.src.ApplicationClass.getRetrofit;

public class CalendarService {
    final CalendarActivityView calendarActivityView;

    public CalendarService(CalendarActivityView calendarActivityView) {
        this.calendarActivityView = calendarActivityView;
    }

    void getGoalOngoing() {
        final CalendarRetrofitInterface calendarRetrofitInterface = getRetrofit().create(CalendarRetrofitInterface.class);
        calendarRetrofitInterface.getGoalOngoing().enqueue(new Callback<CalendarResponse>() {
            @Override
            public void onResponse(Call<CalendarResponse> call, Response<CalendarResponse> response) {
                final CalendarResponse calendarResponse = response.body();
                if (calendarResponse == null) {
                    calendarActivityView.validateUserFail(response.message());
                    return;
                }
                calendarActivityView.validateUserSuccess(calendarResponse.getResult(), calendarResponse.getIsSuccess(), calendarResponse.getCode(), calendarResponse.getMessage());
            }

            @Override
            public void onFailure(Call<CalendarResponse> call, Throwable t) {
                calendarActivityView.validateUserFail(t.getMessage());
            }
        });
    }
}

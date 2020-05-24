package com.example.carrotandstick.src.calendar2;

import com.example.carrotandstick.src.calendar.interfaces.CalendarActivityView;
import com.example.carrotandstick.src.calendar.interfaces.CalendarRetrofitInterface;
import com.example.carrotandstick.src.calendar2.interfaces.Calendar2ActivityView;
import com.example.carrotandstick.src.calendar2.interfaces.Calendar2RetrofitInterface;
import com.example.carrotandstick.src.calendar2.models.GoalNoResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.carrotandstick.src.ApplicationClass.getRetrofit;

public class Calendar2Service {
    final Calendar2ActivityView calendar2ActivityView;

    public Calendar2Service(Calendar2ActivityView calendar2ActivityView) {
        this.calendar2ActivityView = calendar2ActivityView;
    }

    public void getGoalNo() {
        final Calendar2RetrofitInterface calendar2RetrofitInterface = getRetrofit().create(Calendar2RetrofitInterface.class);
        calendar2RetrofitInterface.getGoal().enqueue(new Callback<GoalNoResponse>() {
            @Override
            public void onResponse(Call<GoalNoResponse> call, Response<GoalNoResponse> response) {
                final GoalNoResponse goalNoResponse = response.body();
                calendar2ActivityView.validateGoalNoSuccess(goalNoResponse.getResult(), goalNoResponse.getIsSuccess(), goalNoResponse.getCode(), goalNoResponse.getMessage());
            }

            @Override
            public void onFailure(Call<GoalNoResponse> call, Throwable t) {
                calendar2ActivityView.validateGoalNoFail(t.getMessage());
            }
        });
    }
}

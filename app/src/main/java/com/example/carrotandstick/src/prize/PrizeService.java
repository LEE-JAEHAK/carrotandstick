package com.example.carrotandstick.src.prize;

import com.example.carrotandstick.src.calendar.interfaces.CalendarActivityView;
import com.example.carrotandstick.src.calendar.interfaces.CalendarRetrofitInterface;
import com.example.carrotandstick.src.calendar.models.GoalOngoingResponse;
import com.example.carrotandstick.src.prize.interfaces.PrizeActivityView;
import com.example.carrotandstick.src.prize.interfaces.PrizeRetrofitInterface;
import com.example.carrotandstick.src.prize.models.PrizeResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.carrotandstick.src.ApplicationClass.getRetrofit;

public class PrizeService {
    final PrizeActivityView prizeActivityView;

    public PrizeService(PrizeActivityView prizeActivityView) {
        this.prizeActivityView = prizeActivityView;
    }

    public void getPrize() {
        final PrizeRetrofitInterface prizeRetrofitInterface = getRetrofit().create(PrizeRetrofitInterface.class);
        prizeRetrofitInterface.getPrize().enqueue(new Callback<PrizeResponse>() {
            @Override
            public void onResponse(Call<PrizeResponse> call, Response<PrizeResponse> response) {
                final PrizeResponse prizeResponse = response.body();
                prizeActivityView.validatePrizeSuccess(prizeResponse.getResult(), prizeResponse.getIsSuccess(), prizeResponse.getCode(), prizeResponse.getMessage());
            }

            @Override
            public void onFailure(Call<PrizeResponse> call, Throwable t) {
                prizeActivityView.validatePrizeFail(t.getMessage());
            }
        });
    }
}

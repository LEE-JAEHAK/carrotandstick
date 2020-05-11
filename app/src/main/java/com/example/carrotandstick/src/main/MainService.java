package com.example.carrotandstick.src.main;

import com.example.carrotandstick.src.main.interfaces.MainActivityView;
import com.example.carrotandstick.src.main.interfaces.MainRetrofitInterface;
import com.example.carrotandstick.src.main.models.MainResponse;
import com.example.carrotandstick.src.main.models.RequestGoal;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.carrotandstick.src.ApplicationClass.getRetrofit;

public class MainService {
    final MainActivityView mainActivityView;

    public MainService(MainActivityView mainActivityView) {
        this.mainActivityView = mainActivityView;
    }

    void postUser(RequestGoal requestGoal) {
        final MainRetrofitInterface mainRetrofitInterface = getRetrofit().create(MainRetrofitInterface.class);
        mainRetrofitInterface.postGoal(requestGoal).enqueue(new Callback<MainResponse>() {
            @Override
            public void onResponse(Call<MainResponse> call, Response<MainResponse> response) {
                final MainResponse mainResponse = response.body();
                if (mainResponse == null) {
                    mainActivityView.validateUserFail(response.message());
                    return;
                }
                mainActivityView.validateUserSuccess(mainResponse.getIsSuccess(), mainResponse.getCode(), mainResponse.getMessage());
            }

            @Override
            public void onFailure(Call<MainResponse> call, Throwable t) {
                mainActivityView.validateUserFail(t.getMessage());
            }
        });
    }
}

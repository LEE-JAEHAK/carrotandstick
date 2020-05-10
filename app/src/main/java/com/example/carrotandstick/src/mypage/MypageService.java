package com.example.carrotandstick.src.mypage;

import com.example.carrotandstick.src.mypage.interfaces.MypageActivityView;
import com.example.carrotandstick.src.mypage.interfaces.MypageRetrofitInterface;
import com.example.carrotandstick.src.mypage.models.MypageResponse;

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
        mypageRetrofitInterface.getUser().enqueue(new Callback<MypageResponse>() {
            @Override
            public void onResponse(Call<MypageResponse> call, Response<MypageResponse> response) {
                final MypageResponse mypageResponse = response.body();
                if (mypageResponse == null) {
                    mypageActivityView.validateUserFail(response.message());
                    return;
                }
                mypageActivityView.validateUserSuccess(mypageResponse.getResult(), mypageResponse.getIsSuccess(), mypageResponse.getCode(), mypageResponse.getMessage());
            }

            @Override
            public void onFailure(Call<MypageResponse> call, Throwable t) {
                mypageActivityView.validateUserFail(t.getMessage());
            }
        });
    }
}

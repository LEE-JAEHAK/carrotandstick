package com.example.carrotandstick.src.register;

import com.example.carrotandstick.src.register.interfaces.RegisterActivityView;
import com.example.carrotandstick.src.register.interfaces.RegisterRetrofitInterface;
import com.example.carrotandstick.src.register.models.RegisterResponse;
import com.example.carrotandstick.src.register.models.RequestUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.carrotandstick.src.ApplicationClass.getRetrofit;

public class RegisterService {
    final RegisterActivityView registerActivityView;

    public RegisterService(RegisterActivityView registerActivityView) {
        this.registerActivityView = registerActivityView;
    }

    void postUser(RequestUser requestUser) {
        final RegisterRetrofitInterface registerRetrofitInterface = getRetrofit().create(RegisterRetrofitInterface.class);
        registerRetrofitInterface.postUser(requestUser).enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                final RegisterResponse registerResponse = response.body();
                if (registerResponse == null) {
                    registerActivityView.validateUserFail(response.message());
                    return;
                }
                registerActivityView.validateUserSuccess(registerResponse.getIsSuccess(), registerResponse.getCode(), registerResponse.getMessage());
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                registerActivityView.validateUserFail(t.getMessage());
            }
        });
    }
}

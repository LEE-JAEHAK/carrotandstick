package com.example.carrotandstick.src.login;

import com.example.carrotandstick.src.login.interfaces.LoginActivityView;
import com.example.carrotandstick.src.login.interfaces.LoginRetrofitInterface;
import com.example.carrotandstick.src.login.models.LoginResponse;
import com.example.carrotandstick.src.login.models.RequestUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.carrotandstick.src.ApplicationClass.getRetrofit;

public class LoginService {
    final LoginActivityView loginActivityView;

    public LoginService(LoginActivityView loginActivityView) {
        this.loginActivityView = loginActivityView;
    }

    void postUser(RequestUser requestUser) {
        final LoginRetrofitInterface loginRetrofitInterface = getRetrofit().create(LoginRetrofitInterface.class);
        loginRetrofitInterface.postUser(requestUser).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                final LoginResponse loginResponse = response.body();
                if (loginResponse == null) {
                    loginActivityView.validateUserFail(response.message());
                    return;
                }
                loginActivityView.validateUserSuccess(loginResponse.getResult(), loginResponse.getIsSuccess(), loginResponse.getCode(), loginResponse.getMessage());
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                loginActivityView.validateUserFail(t.getMessage());
            }
        });
    }
}

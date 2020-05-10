package com.example.carrotandstick.src.login.interfaces;

import com.example.carrotandstick.src.login.models.LoginResponse;

public interface LoginActivityView {
    void validateUserSuccess(LoginResponse.Result result, boolean isSuccess, int code, String message);
    void validateUserFail(String msg);
}

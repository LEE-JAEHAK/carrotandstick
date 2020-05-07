package com.example.carrotandstick.src.login.interfaces;

public interface LoginActivityView {
    void validateUserSuccess(boolean isSuccess, int code, String message);
    void validateUserFail();
}

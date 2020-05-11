package com.example.carrotandstick.src.main.interfaces;

public interface MainActivityView {
    void validateUserSuccess(boolean isSuccess, int code, String message);
    void validateUserFail(String msg);
}

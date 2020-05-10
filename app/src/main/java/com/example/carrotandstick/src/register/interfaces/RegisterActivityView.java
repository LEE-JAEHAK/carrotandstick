package com.example.carrotandstick.src.register.interfaces;


public interface RegisterActivityView {
    void validateUserSuccess(boolean isSuccess, int code, String message);
    void validateUserFail(String msg);
}

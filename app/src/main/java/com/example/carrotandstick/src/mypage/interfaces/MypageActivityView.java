package com.example.carrotandstick.src.mypage.interfaces;


import com.example.carrotandstick.src.mypage.models.MypageResponse;

public interface MypageActivityView {
    void validateUserSuccess(MypageResponse.Result result, boolean isSuccess, int code, String message);
    void validateUserFail(String msg);
}

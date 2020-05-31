package com.example.carrotandstick.src.prize.interfaces;


import com.example.carrotandstick.src.calendar.models.GoalOngoingResponse;
import com.example.carrotandstick.src.prize.models.PrizeResponse;

import java.util.ArrayList;

public interface PrizeActivityView {
    void validatePrizeSuccess(ArrayList<PrizeResponse.Result> result, boolean isSuccess, int code, String message);

    void validatePrizeFail(String msg);
}

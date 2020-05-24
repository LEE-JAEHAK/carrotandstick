package com.example.carrotandstick.src.calendar2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.carrotandstick.R;
import com.example.carrotandstick.src.BaseActivity;
import com.example.carrotandstick.src.calendar2.interfaces.Calendar2ActivityView;
import com.example.carrotandstick.src.calendar2.models.GoalNoResponse;

public class CalendarActivity extends BaseActivity implements Calendar2ActivityView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        Calendar2Service calendar2Service = new Calendar2Service(this);
        calendar2Service.getGoalNo();
    }

    @Override
    public void validateGoalNoSuccess(GoalNoResponse.Result result, boolean isSuccess, int code, String message) {

    }

    @Override
    public void validateGoalNoFail(String msg) {
        showCustomToast(msg);
    }
}

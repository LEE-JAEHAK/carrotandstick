package com.example.carrotandstick.src.calendar2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.example.carrotandstick.R;
import com.example.carrotandstick.src.BaseActivity;
import com.example.carrotandstick.src.MainActivity;
import com.example.carrotandstick.src.calendar.CalendarFragment;
import com.example.carrotandstick.src.calendar2.interfaces.Calendar2ActivityView;
import com.example.carrotandstick.src.calendar2.models.GoalNoResponse;
import com.example.carrotandstick.src.calendar2.models.RequestCheck;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.example.carrotandstick.src.ApplicationClass.DATE_FORMAT;

public class CalendarActivity extends BaseActivity implements Calendar2ActivityView {

    List<EventDay> events;
    Calendar calendar;
    CalendarView calendarView;
    Button mBtnGoalcheck;
    Button mBtnGoalfinish;
    CustomDialogDeleteGoal customDialogDeleteGoal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        calendarView = findViewById(R.id.calendarView);

        final int goalNo = getIntent().getIntExtra("goalNo", 1);
        String dday = getIntent().getStringExtra("goalDday");
        String goalName = getIntent().getStringExtra("goalName");

        final Calendar2Service calendar2Service = new Calendar2Service(this);
        mBtnGoalcheck = findViewById(R.id.calendar2_btn_goalcheck);


        TextView mTvDday = findViewById(R.id.calendar2_tv_dday);
        TextView mTvGoalname = findViewById(R.id.calendar2_tv_goal);
        mTvDday.setText(dday);
        mTvGoalname.setText(goalName);

        events = new ArrayList<>();
        calendar = Calendar.getInstance();

        calendar2Service.getGoalNo(goalNo);

        mBtnGoalcheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestCheck requestCheck = new RequestCheck();
                requestCheck.setGoalNo(goalNo);
                calendar2Service.postCheck(requestCheck);
            }
        });

        mBtnGoalfinish=findViewById(R.id.calendar2_btn_finish);
        mBtnGoalfinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDialogDeleteGoal = new CustomDialogDeleteGoal(CalendarActivity.this,positive,negative);
                customDialogDeleteGoal.show();
            }
            private View.OnClickListener positive = new View.OnClickListener() {
                public void onClick(View v) {
                    RequestCheck requestCheck = new RequestCheck();
                    requestCheck.setGoalNo(goalNo);
                    calendar2Service.deleteCheck(requestCheck);

                    customDialogDeleteGoal.dismiss();
                }
            };

            private View.OnClickListener negative = new View.OnClickListener() {
                public void onClick(View v) {
                    customDialogDeleteGoal.dismiss();
                }
            };
        });

    }

    @Override
    public void validateGoalNoSuccess(GoalNoResponse.Result result, boolean isSuccess, int code, String message) {
        if (isSuccess) {
            int size = result.getCheckResult().size();
            for (int i = 0; i < size; i++) {
                String str = result.getCheckResult().get(i).getCreatedAt().substring(0, 10);
                try {
                    Date date = DATE_FORMAT.parse(str);
                    calendar = Calendar.getInstance();
                    calendar.setTime(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                events.add(new EventDay(calendar, R.drawable.calendar2_ic));
            }
            System.out.println("호출 후 : " + events.size());
            calendarView.setEvents(events);
        } else {
            showCustomToast(message);
        }
    }

    @Override
    public void validateGoalNoFail(String msg) {
        showCustomToast(msg);
    }

    @Override
    public void validateCheckSuccess(boolean isSuccess, int code, String message) {
        if (isSuccess) {
            showCustomToast(message);
            Calendar calendar2 = Calendar.getInstance();
            events.add(0, new EventDay(calendar2, R.drawable.calendar2_ic));
            System.out.println("추가 : " + events.size());
            calendarView.setEvents(events);

        } else {
            events.remove(0);
            System.out.println("제거 : " + events.size());
            calendarView.setEvents(events);
            showCustomToast(message);
        }
    }

    @Override
    public void validateCheckFail(String msg) {
        showCustomToast(msg);
    }

    @Override
    public void validateDeleteSuccess(boolean isSuccess, int code, String message) {
        if(isSuccess){
            showCustomToast(message);
            Intent intent = new Intent(CalendarActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }else{
            showCustomToast(message);
        }
    }

    @Override
    public void validateDeleteFail(String msg) {
        showCustomToast(msg);
    }
}

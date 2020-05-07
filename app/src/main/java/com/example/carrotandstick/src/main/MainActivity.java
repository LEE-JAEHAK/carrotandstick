package com.example.carrotandstick.src.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.carrotandstick.R;
import com.example.carrotandstick.src.BaseActivity;
import com.example.carrotandstick.src.calendar.CalendarFragment;
import com.example.carrotandstick.src.mypage.MypageFragment;
import com.example.carrotandstick.src.prize.PrizeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends BaseActivity {
    BottomNavigationView bottomNavigationView;
    MainFragment mainFragment;
    CalendarFragment calendarFragment;
    PrizeFragment prizeFragment;
    MypageFragment mypageFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottom_menu);

        mainFragment = new MainFragment();
        calendarFragment = new CalendarFragment();
        prizeFragment = new PrizeFragment();
        mypageFragment = new MypageFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.main_framelayout, mainFragment).commitAllowingStateLoss();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    //menu_bottom.xml에서 지정해줬던 아이디 값을 받아와서 각 아이디값마다 다른 이벤트를 발생시킵니다.
                    case R.id.underbar_btn_main: {
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_framelayout, mainFragment).commitAllowingStateLoss();
                        return true;
                    }
                    case R.id.underbar_btn_calendar: {
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_framelayout, calendarFragment).commitAllowingStateLoss();
                        return true;
                    }
                    case R.id.underbar_btn_prize: {
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_framelayout, prizeFragment).commitAllowingStateLoss();
                        return true;
                    }
                    case R.id.underbar_btn_mypage: {
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_framelayout, mypageFragment).commitAllowingStateLoss();
                        return true;
                    }
                    default:
                        return false;
                }
            }
        });
    }
}

package com.example.carrotandstick;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.carrotandstick.src.MainActivity;
import com.example.carrotandstick.src.login.LoginActivity;
import com.example.carrotandstick.src.register.RegisterActivity;

import static com.example.carrotandstick.src.ApplicationClass.sSharedPreferences;

public class StartActivity extends AppCompatActivity {
    Button mBtnRegister, mBtnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        if (sSharedPreferences.getString("X-ACCESS-TOKEN", "").equals("")) {
            mBtnRegister = findViewById(R.id.start_btn_register);
            mBtnLogin = findViewById(R.id.start_btn_login);

            mBtnRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(StartActivity.this, RegisterActivity.class);
                    startActivity(intent);
                }
            });

            mBtnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(StartActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            });
        } else {
            Intent intent = new Intent(StartActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}

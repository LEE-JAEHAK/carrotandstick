package com.example.carrotandstick.src.register;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.carrotandstick.R;
import com.example.carrotandstick.src.BaseActivity;
import com.example.carrotandstick.src.MainActivity;
import com.example.carrotandstick.src.register.interfaces.RegisterActivityView;
import com.example.carrotandstick.src.register.models.RegisterResponse;
import com.example.carrotandstick.src.register.models.RequestUser;

import static com.example.carrotandstick.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.example.carrotandstick.src.ApplicationClass.sSharedPreferences;

public class RegisterActivity extends BaseActivity implements RegisterActivityView {

    EditText mEtEmail, mEtPwd, mEtNickname;
    Button mBtnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        mEtEmail = findViewById(R.id.register_et_email);
        mEtPwd = findViewById(R.id.register_et_pwd);
        mEtNickname = findViewById(R.id.register_et_nickname);
        mBtnLogin = findViewById(R.id.register_btn);

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginStart();
            }
        });
    }

    public void loginStart() {
        RegisterService registerService = new RegisterService(this);
        RequestUser requestUser = new RequestUser();
        requestUser.setId(mEtEmail.getText().toString());
        requestUser.setPw(mEtPwd.getText().toString());
        requestUser.setNickName(mEtNickname.getText().toString());
        registerService.postUser(requestUser);
    }

    @Override
    public void validateUserSuccess(boolean isSuccess, int code, String message) {
        if (isSuccess) {
            showCustomToast(message);

            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        } else {
            showCustomToast(message);
        }
    }

    @Override
    public void validateUserFail(String msg) {
        showCustomToast(msg);
    }
}

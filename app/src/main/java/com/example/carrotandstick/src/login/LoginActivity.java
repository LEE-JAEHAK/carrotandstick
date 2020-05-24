package com.example.carrotandstick.src.login;


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
import com.example.carrotandstick.src.login.interfaces.LoginActivityView;
import com.example.carrotandstick.src.login.models.LoginResponse;
import com.example.carrotandstick.src.login.models.RequestUser;

import static com.example.carrotandstick.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.example.carrotandstick.src.ApplicationClass.sSharedPreferences;

public class LoginActivity extends BaseActivity implements LoginActivityView {

    EditText mEtEmail, mEtPwd;
    Button mBtnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        mEtEmail = findViewById(R.id.login_et_email);
        mEtPwd = findViewById(R.id.login_et_pwd);
        mBtnLogin = findViewById(R.id.login_btn);

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginStart();
            }
        });
    }

    public void loginStart() {
        LoginService loginService = new LoginService(this);
        RequestUser requestUser = new RequestUser();
        requestUser.setId(mEtEmail.getText().toString());
        requestUser.setPw(mEtPwd.getText().toString());
        loginService.postUser(requestUser);
    }

    @Override
    public void validateUserSuccess(LoginResponse.Result result, boolean isSuccess, int code, String message) {
        if (isSuccess) {
            showCustomToast(message);

            SharedPreferences.Editor editor = sSharedPreferences.edit();
            editor.putString(X_ACCESS_TOKEN, result.getJwt());
            editor.commit();

            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
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

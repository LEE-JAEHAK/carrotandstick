package com.example.carrotandstick.src.mypage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.carrotandstick.R;
import com.example.carrotandstick.src.login.LoginActivity;
import com.example.carrotandstick.src.MainActivity;
import com.example.carrotandstick.src.mypage.interfaces.MypageActivityView;
import com.example.carrotandstick.src.mypage.models.MypageResponse;
import com.example.carrotandstick.src.register.RegisterActivity;

import static com.example.carrotandstick.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.example.carrotandstick.src.ApplicationClass.sSharedPreferences;

public class MypageFragment extends Fragment implements MypageActivityView {
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = (MainActivity) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activity = null;
    }

    MainActivity activity;
    View view;
    TextView mTvNickname;
    TextView mTvLogin;
    TextView mTvRegister;
    CustomDialogLogout customDialogLogout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.mypage_fragment, container, false);
        mTvLogin = view.findViewById(R.id.mypage_btn_login);
        mTvRegister = view.findViewById(R.id.mypage_btn_register);
        mTvNickname = view.findViewById(R.id.mypage_tv_nickname);

        if (sSharedPreferences.getString("X-ACCESS-TOKEN", "").equals("")) {
            mTvLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity, LoginActivity.class);
                    startActivity(intent);
                }
            });

            mTvRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity, RegisterActivity.class);
                    startActivity(intent);
                }
            });
        } else {
            MypageService mypageService = new MypageService(this);
            mypageService.getUser();

            mTvRegister.setText("로그아웃");
            mTvRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    customDialogLogout = new CustomDialogLogout(activity, positive, negative);
                    customDialogLogout.show();
                }

                private View.OnClickListener positive = new View.OnClickListener() {
                    public void onClick(View v) {
                        SharedPreferences.Editor editor = sSharedPreferences.edit();
                        editor.putString(X_ACCESS_TOKEN, "");
                        editor.commit();

                        Intent intent = new Intent(activity, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        customDialogLogout.dismiss();
                    }
                };

                private View.OnClickListener negative = new View.OnClickListener() {
                    public void onClick(View v) {
                        customDialogLogout.dismiss();
                    }
                };

            });
            mTvLogin.setText("탈퇴하기");
        }


        return view;
    }

    @Override
    public void validateUserSuccess(MypageResponse.Result result, boolean isSuccess, int code, String message) {
        if (isSuccess)
            mTvNickname.setText(result.getNickName());
        else
            activity.showCustomToast(message);
    }

    @Override
    public void validateUserFail(String msg) {
        activity.showCustomToast(msg);
    }
}

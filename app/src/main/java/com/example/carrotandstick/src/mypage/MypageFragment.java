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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carrotandstick.R;
import com.example.carrotandstick.StartActivity;
import com.example.carrotandstick.src.mypage.models.GoalOngoingResponse;
import com.example.carrotandstick.src.login.LoginActivity;
import com.example.carrotandstick.src.MainActivity;
import com.example.carrotandstick.src.mypage.interfaces.MypageActivityView;
import com.example.carrotandstick.src.mypage.models.FinishedgoalResponse;
import com.example.carrotandstick.src.mypage.models.UserInfoResponse;
import com.example.carrotandstick.src.mypage.recyclerview.RecyclerviewAdapter1;
import com.example.carrotandstick.src.mypage.recyclerview.RecyclerviewAdapter2;
import com.example.carrotandstick.src.register.RegisterActivity;

import java.util.ArrayList;

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
    TextView mTvNickname, mTvLogin, mTvRegister;
    CustomDialogLogout customDialogLogout;
    ArrayList<GoalOngoingResponse.Result> arrayList = new ArrayList<>();
    ArrayList<FinishedgoalResponse.Result> arrayList2 = new ArrayList<>();
    RecyclerView recyclerView1, recyclerView2;
    RecyclerviewAdapter1 recyclerviewAdapter1;
    RecyclerviewAdapter2 recyclerviewAdapter2;

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

                        Intent intent = new Intent(activity, StartActivity.class);
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

            mypageService.getGoalOngoing();
            mypageService.getGoalFinished();

            recyclerView1 = view.findViewById(R.id.recyclerview1);
            recyclerviewAdapter1 = new RecyclerviewAdapter1(arrayList,activity);
            recyclerView1.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false));
            recyclerView1.setAdapter(recyclerviewAdapter1);

            recyclerView2 = view.findViewById(R.id.recyclerview2);
            recyclerviewAdapter2 = new RecyclerviewAdapter2(arrayList2,activity);
            recyclerView2.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false));
            recyclerView2.setAdapter(recyclerviewAdapter2);
        }

        return view;
    }

    @Override
    public void validateUserInfoSuccess(UserInfoResponse.Result result, boolean isSuccess, int code, String message) {
        if (isSuccess)
            mTvNickname.setText(result.getNickName());
        else
            activity.showCustomToast(message);
    }

    @Override
    public void validateUserInfoFail(String msg) {
        activity.showCustomToast(msg);
    }

    @Override
    public void validateGoalOngoingSuccess(ArrayList<GoalOngoingResponse.Result> result, boolean isSuccess, int code, String message) {
        if (isSuccess) {
            arrayList.clear();
            arrayList.addAll(result);
            recyclerviewAdapter1.notifyDataSetChanged();
        } else {
            activity.showCustomToast(message);
        }
    }

    @Override
    public void validateGoalOngoingFail(String msg) {
        activity.showCustomToast(msg);
    }

    @Override
    public void validateGoalFinishedSuccess(ArrayList<FinishedgoalResponse.Result> result, boolean isSuccess, int code, String message) {
        if (isSuccess) {
            arrayList2.clear();
            arrayList2.addAll(result);
            recyclerviewAdapter2.notifyDataSetChanged();
        } else {
            activity.showCustomToast(message);
        }
    }

    @Override
    public void validateGoalFinishedFail(String msg) {
        activity.showCustomToast(msg);
    }
}

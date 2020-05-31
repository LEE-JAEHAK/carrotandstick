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
import com.example.carrotandstick.StartActivity;
import com.example.carrotandstick.src.calendar.models.GoalOngoingResponse;
import com.example.carrotandstick.src.login.LoginActivity;
import com.example.carrotandstick.src.MainActivity;
import com.example.carrotandstick.src.mypage.interfaces.MypageActivityView;
import com.example.carrotandstick.src.mypage.listview.ListviewAdapter;
import com.example.carrotandstick.src.mypage.listview.ListviewAdapter2;
import com.example.carrotandstick.src.mypage.models.FinishedgoalResponse;
import com.example.carrotandstick.src.mypage.models.UserInfoResponse;
import com.example.carrotandstick.src.register.RegisterActivity;
import com.github.paolorotolo.expandableheightlistview.ExpandableHeightListView;

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
    TextView mTvNickname;
    TextView mTvLogin;
    TextView mTvRegister;
    CustomDialogLogout customDialogLogout;
    ExpandableHeightListView listView, listView2;
    ListviewAdapter listviewAdapter;
    ListviewAdapter2 listviewAdapter2;
    ArrayList<GoalOngoingResponse.Result> arrayList = new ArrayList<>();
    ArrayList<FinishedgoalResponse.Result> arrayList2 = new ArrayList<>();



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

            listView = view.findViewById(R.id.calendar_listview);
            listView2 = view.findViewById(R.id.calendar_listview2);
            listviewAdapter = new ListviewAdapter(arrayList, activity);
            listviewAdapter2 = new ListviewAdapter2(arrayList2, activity);
            listView.setExpanded(true);
            listView2.setExpanded(true);
            listView.setAdapter(listviewAdapter);
            listView2.setAdapter(listviewAdapter2);
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
            listviewAdapter.notifyDataSetChanged();
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
            listviewAdapter2.notifyDataSetChanged();
        } else {
            activity.showCustomToast(message);
        }
    }

    @Override
    public void validateGoalFinishedFail(String msg) {
        activity.showCustomToast(msg);
    }
}

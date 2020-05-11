package com.example.carrotandstick.src.main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.carrotandstick.R;
import com.example.carrotandstick.src.MainActivity;
import com.example.carrotandstick.src.calendar.CalendarFragment;
import com.example.carrotandstick.src.main.interfaces.MainActivityView;
import com.example.carrotandstick.src.main.models.RequestGoal;

public class MainFragment extends Fragment implements MainActivityView {
    MainActivity activity;
    View view;
    EditText mEtGoal;
    Button mBtnGoal;
    CustomDialogMain customDialogMain;

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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.main_fragment, container, false);
        mEtGoal = view.findViewById(R.id.main_et_goal);
        mBtnGoal = view.findViewById(R.id.main_btn_goal);

        mBtnGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDialogMain = new CustomDialogMain(activity, positive, negative);
                customDialogMain.show();
            }
        });

        return view;
    }

    private View.OnClickListener positive = new View.OnClickListener() {
        public void onClick(View v) {
            setGoal();
            customDialogMain.dismiss();
        }
    };

    private View.OnClickListener negative = new View.OnClickListener() {
        public void onClick(View v) {
            customDialogMain.dismiss();
        }
    };

    void setGoal() {
        MainService mainService = new MainService(this);
        RequestGoal requestGoal = new RequestGoal();
        requestGoal.setGoal(mEtGoal.getText().toString());
        mainService.postUser(requestGoal);
    }

    public void switchToFragment() {
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.main_framelayout, new CalendarFragment()).commitAllowingStateLoss();
    }

    @Override
    public void validateUserSuccess(boolean isSuccess, int code, String message) {
        if (isSuccess) {
            activity.showCustomToast(message);
            mEtGoal.setText("");

            switchToFragment();

        } else {
            activity.showCustomToast(message);
        }
    }

    @Override
    public void validateUserFail(String msg) {
        activity.showCustomToast(msg);
    }
}

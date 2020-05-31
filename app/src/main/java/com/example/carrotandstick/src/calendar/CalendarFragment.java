package com.example.carrotandstick.src.calendar;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.carrotandstick.R;
import com.example.carrotandstick.src.MainActivity;
import com.example.carrotandstick.src.calendar.gridview.ExpandableHeightGridView;
import com.example.carrotandstick.src.calendar.gridview.GridviewAdapter;
import com.example.carrotandstick.src.calendar.interfaces.CalendarActivityView;
import com.example.carrotandstick.src.calendar.models.GoalOngoingResponse;
import com.example.carrotandstick.src.calendar2.models.GoalNoResponse;

import java.util.ArrayList;

public class CalendarFragment extends Fragment implements CalendarActivityView {
    MainActivity activity;
    View view;
    ExpandableHeightGridView gridView;
    GridviewAdapter gridviewAdapter;
    ArrayList<GoalOngoingResponse.Result> arrayList = new ArrayList<>();

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
        view = inflater.inflate(R.layout.calendar_fragment, container, false);

        CalendarService calendarService = new CalendarService(this);
        calendarService.getGoalOngoing();

        gridView = view.findViewById(R.id.calendar_gridview);
        gridView.setExpanded(true);
        gridviewAdapter = new GridviewAdapter(arrayList, activity);
        gridView.setAdapter(gridviewAdapter);

        return view;
    }

    @Override
    public void validateOngoingSuccess(ArrayList<GoalOngoingResponse.Result> result, boolean isSuccess, int code, String message) {
        if(isSuccess){
            arrayList.clear();
            arrayList.addAll(result);
            gridviewAdapter.notifyDataSetChanged();
        }
        else{
            activity.showCustomToast(message);
        }
    }

    @Override
    public void validateOngoingFail(String msg) {
        activity.showCustomToast(msg);
    }

    @Override
    public void onResume() {
        System.out.println("onresume");
        CalendarService calendarService = new CalendarService(this);
        calendarService.getGoalOngoing();
        super.onResume();
    }
}

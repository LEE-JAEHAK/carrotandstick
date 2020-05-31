package com.example.carrotandstick.src.prize;

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
import com.example.carrotandstick.src.prize.gridview.PrizeGridviewAdapter;
import com.example.carrotandstick.src.prize.interfaces.PrizeActivityView;
import com.example.carrotandstick.src.prize.models.PrizeResponse;

import java.util.ArrayList;

public class PrizeFragment extends Fragment implements PrizeActivityView {
    MainActivity activity;
    View view;
    ExpandableHeightGridView gridView;
    ArrayList<PrizeResponse.Result> arrayList = new ArrayList<>();
    PrizeGridviewAdapter prizeGridviewAdapter;

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
        view = inflater.inflate(R.layout.prize_fragment, container, false);
        gridView = view.findViewById(R.id.prize_gridview);
        gridView.setExpanded(true);

        PrizeService prizeService = new PrizeService(this);
        prizeService.getPrize();

        prizeGridviewAdapter = new PrizeGridviewAdapter(arrayList, activity);
        gridView.setAdapter(prizeGridviewAdapter);

        return view;
    }

    @Override
    public void validatePrizeSuccess(ArrayList<PrizeResponse.Result> result, boolean isSuccess, int code, String message) {
        if(isSuccess){
            activity.showCustomToast(message);
            arrayList.clear();
            arrayList.addAll(result);
            prizeGridviewAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void validatePrizeFail(String msg) {
        activity.showCustomToast(msg);
    }
}

package com.example.carrotandstick.src.prize;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.carrotandstick.R;
import com.example.carrotandstick.src.calendar.ExpandableHeightGridView;
import com.example.carrotandstick.src.calendar.GridviewAdapter;

import java.util.ArrayList;

public class PrizeFragment extends Fragment {
    View view;
    ExpandableHeightGridView gridView;
    ArrayList<PrizeItem> arrayList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.prize_fragment, container, false);
        gridView = view.findViewById(R.id.prize_gridview);
        gridView.setExpanded(true);


        arrayList.add(new PrizeItem("시들시들 당근x1"));
        arrayList.add(new PrizeItem("영양 가득 당근x1"));
        arrayList.add(new PrizeItem("쥐가 파먹은 당근x1"));

        PrizeGridviewAdapter gridviewAdapter = new PrizeGridviewAdapter(arrayList, getContext());
        gridView.setAdapter(gridviewAdapter);


        return view;
    }
}

package com.example.carrotandstick.calendar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.carrotandstick.R;

import java.util.ArrayList;

public class CalendarFragment extends Fragment {
    View view;
    ExpandableHeightGridView gridView;
    ArrayList<Item> itemArrayList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.calendar_fragment, container, false);
        gridView = view.findViewById(R.id.calendar_gridview);
        gridView.setExpanded(true);


        itemArrayList.add(new Item("D+3","하루에 \n물 3잔씩 마시기"));
        itemArrayList.add(new Item("D+4","하루에 \n물 5잔씩 마시기"));
        itemArrayList.add(new Item("D+5","하루에 \n물 4잔씩 마시기"));

        GridviewAdapter gridviewAdapter = new GridviewAdapter(itemArrayList,getContext());
        gridView.setAdapter(gridviewAdapter);

        return view;
    }
}

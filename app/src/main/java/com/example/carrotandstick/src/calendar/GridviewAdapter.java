package com.example.carrotandstick.src.calendar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.carrotandstick.R;

import java.util.ArrayList;

public class GridviewAdapter extends BaseAdapter {
    ArrayList<Item> itemArrayList;
    LayoutInflater layoutInflater;
    Context context;

    public GridviewAdapter(ArrayList<Item> itemArrayList, Context context) {
        this.itemArrayList = itemArrayList;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
    }

    @Override
    public int getCount() {
        return itemArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.goalitem_gridview, parent, false);

        Item item = itemArrayList.get(position);

        TextView textView = convertView.findViewById(R.id.calendar_tv_dday);
        TextView textView1 = convertView.findViewById(R.id.calendar_tv_goal);

        textView.setText(item.getDday());
        textView1.setText(item.getGoal());


        return convertView;
    }
}

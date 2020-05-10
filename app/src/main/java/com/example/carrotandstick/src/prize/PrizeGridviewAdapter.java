package com.example.carrotandstick.src.prize;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.carrotandstick.R;

import java.util.ArrayList;

public class PrizeGridviewAdapter extends BaseAdapter {
    ArrayList<PrizeItem> itemArrayList;
    LayoutInflater layoutInflater;
    Context context;

    public PrizeGridviewAdapter(ArrayList<PrizeItem> itemArrayList, Context context) {
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
        convertView = layoutInflater.inflate(R.layout.prizeitem_gridview, parent, false);

        PrizeItem item = itemArrayList.get(position);

        TextView textView = convertView.findViewById(R.id.prize_tv_icname);

        textView.setText(item.getPrizeName());

        return convertView;
    }
}

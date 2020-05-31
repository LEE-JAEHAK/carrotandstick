package com.example.carrotandstick.src.prize.gridview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.carrotandstick.R;
import com.example.carrotandstick.src.prize.models.PrizeResponse;

import java.util.ArrayList;

public class PrizeGridviewAdapter extends BaseAdapter {
    ArrayList<PrizeResponse.Result> itemArrayList;
    LayoutInflater layoutInflater;
    Context context;

    public PrizeGridviewAdapter(ArrayList<PrizeResponse.Result> itemArrayList, Context context) {
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

        PrizeResponse.Result item = itemArrayList.get(position);

        ImageView imageView = convertView.findViewById(R.id.prize_iv_ic);
        TextView textView = convertView.findViewById(R.id.prize_tv_icname);
        TextView textView1 = convertView.findViewById(R.id.prize_tv_iccnt);

        Glide.with(context).load(item.getImageUrl()).override(500,500).into(imageView);
        imageView.setClipToOutline(true);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        textView.setText(item.getName());
        textView1.setText("x"+item.getCnt());

        return convertView;
    }
}

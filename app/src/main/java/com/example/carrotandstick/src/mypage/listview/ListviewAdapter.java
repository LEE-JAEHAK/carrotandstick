package com.example.carrotandstick.src.mypage.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.carrotandstick.R;
import com.example.carrotandstick.src.calendar.models.GoalOngoingResponse;

import java.util.ArrayList;

import static com.example.carrotandstick.src.calendar.gridview.GridviewAdapter.getDday;

public class ListviewAdapter extends BaseAdapter {
    private ArrayList<GoalOngoingResponse.Result> arrayList;
    private LayoutInflater layoutInflater;
    private Context context;

    public ListviewAdapter(ArrayList<GoalOngoingResponse.Result> arrayList, Context context) {
        this.arrayList = arrayList;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.goal_listview, parent, false);

        final GoalOngoingResponse.Result item = arrayList.get(position);

        TextView mTvDday = convertView.findViewById(R.id.calendar_tv_dday_listview);
        TextView mTvGoalname = convertView.findViewById(R.id.calendar_tv_goal_listview);
        String year = item.getCreatedAt().substring(0, 4);
        String month = item.getCreatedAt().substring(5, 7);
        String day = item.getCreatedAt().substring(8, 10);
        System.out.println("year : " + year + "  month : " + month + "   day : " + day);
        String dday = getDday(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));

        mTvDday.setText(dday);
        mTvGoalname.setText(item.getGoal());

        return convertView;
    }

}


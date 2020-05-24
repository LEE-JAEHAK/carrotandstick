package com.example.carrotandstick.src.calendar.gridview;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.carrotandstick.R;
import com.example.carrotandstick.src.calendar.models.GoalOngoingResponse;
import com.example.carrotandstick.src.calendar2.CalendarActivity;

import java.util.ArrayList;
import java.util.Calendar;

public class GridviewAdapter extends BaseAdapter {
    ArrayList<GoalOngoingResponse.Result> arrayList;
    LayoutInflater layoutInflater;
    Context context;

    public GridviewAdapter(ArrayList<GoalOngoingResponse.Result> arrayList, Context context) {
        this.arrayList = arrayList;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        convertView = layoutInflater.inflate(R.layout.goalitem_gridview, parent, false);

        final GoalOngoingResponse.Result item = arrayList.get(position);

        LinearLayout mBtnGoal = convertView.findViewById(R.id.calendar_ll_btn);
        TextView mTvDday = convertView.findViewById(R.id.calendar_tv_dday);
        TextView mTvGoalname = convertView.findViewById(R.id.calendar_tv_goal);
        String year = item.getCreatedAt().substring(0, 4);
        String month = item.getCreatedAt().substring(5, 7);
        String day = item.getCreatedAt().substring(8, 10);
        System.out.println("year : " + year + "  month : " + month + "   day : " + day);
        String dday = getDday(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));

        mTvDday.setText(dday);
        mTvGoalname.setText(item.getGoal());

        mBtnGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int no = item.getNo();
                Intent intent = new Intent(context, CalendarActivity.class);
                intent.putExtra("no",no);   // calendar 로 no intent로 넘겨줌
                context.startActivity(intent);
            }
        });

        return convertView;
    }

    final static int ONE_DAY = 24 * 60 * 60 * 1000;

    public static String getDday(int a_year, int a_monthOfYear, int a_dayOfMonth) {
        // D-day 설정
        final Calendar ddayCalendar = Calendar.getInstance();
        ddayCalendar.set(a_year, a_monthOfYear - 1, a_dayOfMonth);

        // D-day 를 구하기 위해 millisecond 으로 환산하여 d-day 에서 today 의 차를 구한다.
        final long dday = ddayCalendar.getTimeInMillis() / ONE_DAY;
        final long today = Calendar.getInstance().getTimeInMillis() / ONE_DAY;
        long result = dday - today;
        System.out.println("dday : " + dday + "   today : " + today + "  result time : " + result);

        // 출력 시 d-day 에 맞게 표시
        final String strFormat;
        if (result > 0) {
            strFormat = "D-%d";
        } else if (result == 0) {
            strFormat = "D-Day";
        } else {
            result *= -1;
            strFormat = "D+%d";
        }

        final String strCount = (String.format(strFormat, result));
        return strCount;
    }
}

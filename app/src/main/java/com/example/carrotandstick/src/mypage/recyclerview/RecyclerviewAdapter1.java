package com.example.carrotandstick.src.mypage.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.carrotandstick.R;
import com.example.carrotandstick.src.mypage.models.GoalOngoingResponse;

import java.util.ArrayList;

import static com.example.carrotandstick.src.calendar.gridview.GridviewAdapter.getDday;

public class RecyclerviewAdapter1 extends RecyclerView.Adapter<RecyclerviewAdapter1.ViewHolder> {
    private ArrayList<GoalOngoingResponse.Result> arrayList;
    private LayoutInflater layoutInflater;
    private Context context;

    public RecyclerviewAdapter1(ArrayList<GoalOngoingResponse.Result> arrayList, Context context) {
        this.arrayList = arrayList;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerviewAdapter1.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.goal_recyclerview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerviewAdapter1.ViewHolder holder, int position) {
        final GoalOngoingResponse.Result item = arrayList.get(position);

        String year = item.getCreatedAt().substring(0, 4);
        String month = item.getCreatedAt().substring(5, 7);
        String day = item.getCreatedAt().substring(8, 10);
        System.out.println("year : " + year + "  month : " + month + "   day : " + day);
        String dday = getDday(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));

        holder.mTvDday.setText(dday);
        holder.mTvGoal.setText(item.getGoal());
        Glide.with(context).load(item.getImageUrl()).into(holder.mIvIc);
        holder.mIvIc.setClipToOutline(true);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTvDday, mTvGoal;
        ImageView mIvIc;
        public ViewHolder(@NonNull View view) {
            super(view);
            mTvDday = view.findViewById(R.id.mypage_tv_dday);
            mTvGoal = view.findViewById(R.id.mypage_tv_goal);
            mIvIc = view.findViewById(R.id.mypage_iv_ic);
        }
    }
}

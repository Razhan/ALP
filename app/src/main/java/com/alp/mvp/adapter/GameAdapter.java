package com.alp.mvp.adapter;

import android.content.Context;
import android.util.Log;

import com.alp.library.widget.recycleview.BasicRecyclerViewAdapter;
import com.alp.library.widget.recycleview.ViewHolder;
import com.alp.mvp.R;

import java.util.List;

import static com.alp.mvp.utils.MiscUtil.identifyLayer;

public class GameAdapter extends BasicRecyclerViewAdapter<String> {

    public GameAdapter(Context context, List<String> list) {
        super(context, list);
    }

    @Override
    public int getItemLayout() {
        return R.layout.item_game;
    }

    @Override
    protected void onBindItemViewHolder(ViewHolder holder, int position, String item) {
        identifyLayer(mContext, holder.itemView, position);

        holder.setOnClickListener(R.id.attendance_wrapper, v -> Log.d("attendance", "attendance"));
    }

}

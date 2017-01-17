package com.alp.mvp.adapter;

import android.content.Context;

import com.alp.library.widget.recycleview.BasicRecyclerViewAdapter;
import com.alp.library.widget.recycleview.ViewHolder;
import com.alp.mvp.R;

import java.util.List;

import static com.alp.mvp.utils.MiscUtil.identifyLayer;

public class AttendPlayerAdapter extends BasicRecyclerViewAdapter<String> {

    public AttendPlayerAdapter(Context context, List<String> list) {
        super(context, list);
    }

    @Override
    public int getItemLayout() {
        return R.layout.item_player_attend;
    }

    @Override
    protected void onBindItemViewHolder(ViewHolder holder, int position, String item) {
        identifyLayer(mContext, holder.itemView, position);

    }

}
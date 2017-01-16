package com.alp.mvp.adapter;

import android.content.Context;

import com.alp.library.widget.recycleview.BasicRecyclerViewAdapter;
import com.alp.library.widget.recycleview.ViewHolder;
import com.alp.mvp.R;

import java.util.List;

public class PenaltyAdapter extends BasicRecyclerViewAdapter<String> {

    public PenaltyAdapter(Context context, List<String> list) {
        super(context, list);
    }

    @Override
    public int getItemLayout() {
        return R.layout.item_penalty;
    }

    @Override
    protected void onBindItemViewHolder(ViewHolder holder, int position, String item) {
    }

}
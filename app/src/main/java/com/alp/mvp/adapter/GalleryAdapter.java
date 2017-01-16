package com.alp.mvp.adapter;

import android.content.Context;

import com.alp.library.widget.recycleview.BasicRecyclerViewAdapter;
import com.alp.library.widget.recycleview.ViewHolder;
import com.alp.mvp.R;

import java.util.List;

public class GalleryAdapter extends BasicRecyclerViewAdapter<String> {

    public GalleryAdapter(Context context, List<String> list) {
        super(context, list);
    }

    @Override
    public int getItemLayout() {
        return R.layout.item_gallery;
    }

    @Override
    protected void onBindItemViewHolder(ViewHolder holder, int position, String item) {
    }

}
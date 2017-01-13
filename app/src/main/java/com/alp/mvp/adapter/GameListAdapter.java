package com.alp.mvp.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.alp.library.widget.recycleview.BasicRecyclerViewAdapter;
import com.alp.library.widget.recycleview.ViewHolder;
import com.alp.mvp.R;

import java.util.List;

public class GameListAdapter extends BasicRecyclerViewAdapter<List<String>> {

    public GameListAdapter(Context context, List<List<String>> list) {
        super(context, list);
    }

    @Override
    public int getItemLayout() {
        return R.layout.item_game_list;
    }

    @Override
    protected void onBindItemViewHolder(ViewHolder holder, int position, List<String> item) {
        GameAdapter adapter = new GameAdapter(mContext, item);
        RecyclerView list = holder.getView(R.id.team_date_list);

        adapter.setClickListener((view, pos, game) -> Log.d("setClickListener", "setClickListener"));

        list.setLayoutManager(new LinearLayoutManager(mContext));
        list.setAdapter(adapter);
    }
}
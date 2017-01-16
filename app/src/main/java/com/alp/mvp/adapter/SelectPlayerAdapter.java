package com.alp.mvp.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.alp.library.widget.recycleview.BasicRecyclerViewAdapter;
import com.alp.library.widget.recycleview.ViewHolder;
import com.alp.mvp.R;

import java.util.List;

import static com.alp.mvp.utils.MiscUtil.identifyLayer;
import static com.google.common.base.Preconditions.checkNotNull;

public class SelectPlayerAdapter extends BasicRecyclerViewAdapter<String> {

    private final static int MAX_GOAL = 1;
    private final static int MAX_ASSIST = 2;
    private final static String STRING_GOAL = "Goal";
    private final static String STRING_ASSIST = "Assist";

    private int goalCount = 0;
    private int assistCount = 0;

    public SelectPlayerAdapter(Context context, List<String> list) {
        super(context, list);
    }

    @Override
    public int getItemLayout() {
        return R.layout.item_player_select;
    }

    @Override
    protected void onBindItemViewHolder(ViewHolder holder, int position, String item) {
        identifyLayer(mContext, holder.itemView, position);
    }

    public void updatePlayer(View view) {
        TextView textView = checkNotNull((TextView)view.findViewById(R.id.indicator));

        if (textView.getVisibility() == View.VISIBLE) {
            if (textView.getText().toString().equals(STRING_GOAL)) {
                goalCount--;
            } else {
                assistCount--;
            }

            textView.setVisibility(View.INVISIBLE);
            return;
        }

        String text;
        if (goalCount < MAX_GOAL) {
            text = STRING_GOAL;
            goalCount++;
        } else if (assistCount < MAX_ASSIST) {
            text = STRING_ASSIST;
            assistCount++;
        } else {
            return;
        }

        textView.setText(text);
        textView.setVisibility(View.VISIBLE);
    }

}
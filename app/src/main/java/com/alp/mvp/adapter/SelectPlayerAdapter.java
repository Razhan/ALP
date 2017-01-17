package com.alp.mvp.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.alp.library.widget.recycleview.BasicRecyclerViewAdapter;
import com.alp.library.widget.recycleview.ViewHolder;
import com.alp.mvp.R;
import com.alp.mvp.games.ui.AddOperationFragment;

import java.util.List;

import static com.alp.mvp.utils.MiscUtil.identifyLayer;
import static com.google.common.base.Preconditions.checkNotNull;

public class SelectPlayerAdapter extends BasicRecyclerViewAdapter<String> {

    private final static int MAX_GOAL = 1;
    private final static int MAX_ASSIST = 2;
    private final static String STRING_GOAL = "Goal";
    private final static String STRING_ASSIST = "Assist";

    private int previousColor = -1;

    private int goalCount = 0;
    private int assistCount = 0;

    private AddOperationFragment.OnGoalSelectListener listener;

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
        TextView indicator = checkNotNull((TextView) view.findViewById(R.id.indicator));
        TextView name = checkNotNull((TextView) view.findViewById(R.id.name));

        if (indicator.getVisibility() == View.VISIBLE) {
            if (indicator.getText().toString().equals(STRING_GOAL)) {
                goalCount--;

                if (listener != null) {
                    listener.onGoalSelect(false);
                }
            } else {
                assistCount--;
            }

            indicator.setVisibility(View.INVISIBLE);
            name.setTextColor(previousColor);
            return;
        }

        String text;
        if (goalCount < MAX_GOAL) {
            text = STRING_GOAL;
            goalCount++;

            if (listener != null) {
                listener.onGoalSelect(true);
            }
        } else if (assistCount < MAX_ASSIST) {
            text = STRING_ASSIST;
            assistCount++;
        } else {
            return;
        }

        indicator.setText(text);

        if (previousColor < 0) {
            previousColor = name.getCurrentTextColor();
        }
        name.setTextColor(ContextCompat.getColor(mContext, R.color.colorPrimary));
        indicator.setVisibility(View.VISIBLE);
    }

    public void setListener(AddOperationFragment.OnGoalSelectListener listener) {
        this.listener = listener;
    }
}
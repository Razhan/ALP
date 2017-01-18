package com.alp.mvp.adapter;

import android.content.Context;
import android.view.View;

import com.alp.library.widget.recycleview.BasicRecyclerViewAdapter;
import com.alp.library.widget.recycleview.ViewHolder;
import com.alp.mvp.R;
import com.alp.mvp.model.AttendDialogItem;
import com.alp.mvp.widget.AttendanceSelectListener;
import com.alp.mvp.widget.ColorfulCircle;

import java.util.List;

import static com.alp.mvp.utils.MiscUtil.buildAttendDialog;
import static com.alp.mvp.utils.MiscUtil.identifyLayer;

public class AttendPlayerAdapter extends BasicRecyclerViewAdapter<String> {

    private AttendanceSelectListener listener;

    public AttendPlayerAdapter(Context context, List<String> list) {
        super(context, list);
    }

    public void setAttendanceListener(AttendanceSelectListener listener) {
        this.listener = (view, item) -> {
            changeAttendance(view, item);

            if (listener != null) {
                listener.onAttendanceSelect(view, item);
            }
        };
    }

    @Override
    public int getItemLayout() {
        return R.layout.item_player_attend;
    }

    @Override
    protected void onBindItemViewHolder(ViewHolder holder, int position, String item) {
        identifyLayer(mContext, holder.itemView, position);

        holder.setOnClickListener(R.id.attend_wrapper, v -> buildAttendDialog(mContext, v, listener).create().show());
    }

    private void changeAttendance(View view, AttendDialogItem item) {
        ((ColorfulCircle) view.findViewById(R.id.indicator)).setColor(item.getColor());
    }

}
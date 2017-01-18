package com.alp.mvp.adapter;

import android.content.Context;
import android.view.View;

import com.alp.library.widget.recycleview.BasicRecyclerViewAdapter;
import com.alp.library.widget.recycleview.ViewHolder;
import com.alp.mvp.R;
import com.alp.mvp.model.AttendDialogItem;
import com.alp.mvp.widget.ColorfulCircle;

import java.util.ArrayList;
import java.util.List;

import static com.alp.mvp.model.AttendDialogItem.COLOR_GREEN;
import static com.alp.mvp.model.AttendDialogItem.COLOR_RED;
import static com.alp.mvp.model.AttendDialogItem.COLOR_YELLOW;

public class AttendDialogAdapter extends BasicRecyclerViewAdapter<AttendDialogItem> {

    private View previous;
    private AttendDialogItem selectedItem;

    public AttendDialogAdapter(Context context, List list) {
        super(context, null);
        init();
    }

    private void init() {
        List<AttendDialogItem> list = new ArrayList<>();

        list.add(new AttendDialogItem(COLOR_RED, "Not attended"));
        list.add(new AttendDialogItem(COLOR_GREEN, "attended"));
        list.add(new AttendDialogItem(COLOR_YELLOW, "Something else"));

        set(list);
    }

    @Override
    public int getItemLayout() {
        return R.layout.item_attend_dialog;
    }

    @Override
    protected void onBindItemViewHolder(ViewHolder holder, int position, AttendDialogItem item) {
        holder.setText(R.id.text, item.getText());
        ((ColorfulCircle) (holder.getView(R.id.indicator))).setColor(item.getColor());
    }

    public void onClick(View view, AttendDialogItem item) {
        if (previous != null) {
            previous.findViewById(R.id.check).setVisibility(View.INVISIBLE);
        }

        view.findViewById(R.id.check).setVisibility(View.VISIBLE);

        previous = view;
        selectedItem = item;
    }

    public AttendDialogItem getSelectItem() {
        return selectedItem;
    }

}

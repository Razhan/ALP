package com.alp.mvp.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.alp.mvp.R;
import com.alp.mvp.adapter.AttendDialogAdapter;
import com.alp.mvp.widget.AttendanceSelectListener;

public final class MiscUtil {

    public static void identifyLayer(Context context, View view, int pos) {
        if (pos % 2 == 0) {
            view.setBackgroundColor(Color.WHITE);
        } else {
            view.setBackgroundColor(ContextCompat.getColor(context, R.color.light_background));
        }
    }

    public static AlertDialog.Builder buildAttendDialog(Context context, View v, AttendanceSelectListener listener) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);

        LayoutInflater inflater = LayoutInflater.from(context);
        final RecyclerView dialogView = (RecyclerView) inflater.inflate(R.layout.view_attend_dialog, null);

        AttendDialogAdapter adapter = new AttendDialogAdapter(context, null);
        adapter.setClickListener((view, pos, item) -> adapter.onClick(view, item));

        dialogView.setLayoutManager(new LinearLayoutManager(context));
        dialogView.setAdapter(adapter);

        dialogBuilder.setView(dialogView)
                .setTitle("Choose Attendance")
                .setPositiveButton("OK", (dialog, which) -> {
                    Log.d("Choose", "Choose");

                    listener.onAttendanceSelect(v, adapter.getSelectItem());

                })
                .setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        return dialogBuilder;
    }

    public static void buildDeleteDialog(Context context, DialogInterface.OnClickListener listener) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        dialogBuilder.setTitle("Delete")
                .setMessage("Are you sure to delete this?")
                .setPositiveButton("OK", listener)
                .setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        dialogBuilder.create().show();
    }

}

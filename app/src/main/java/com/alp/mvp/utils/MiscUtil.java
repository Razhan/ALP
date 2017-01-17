package com.alp.mvp.utils;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.alp.mvp.R;

public final class MiscUtil {

    public static void identifyLayer(Context context, View view, int pos) {
        if (pos % 2 == 0) {
            view.setBackgroundColor(Color.WHITE);
        } else {
            view.setBackgroundColor(ContextCompat.getColor(context, R.color.light_bg));
        }
    }

}

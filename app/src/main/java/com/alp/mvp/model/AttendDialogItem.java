package com.alp.mvp.model;

import android.graphics.Color;

public class AttendDialogItem {

    public static final int COLOR_RED = Color.parseColor("#F1082D");
    public static final int COLOR_GREEN = Color.parseColor("#7FFF00");
    public static final int COLOR_YELLOW = Color.parseColor("#FFFF00");

    private int color;
    private String text;

    public AttendDialogItem(int color, String text) {
        this.color = color;
        this.text = text;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}

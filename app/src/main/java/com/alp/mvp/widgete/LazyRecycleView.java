package com.alp.mvp.widgete;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class LazyRecycleView extends RecyclerView {

    public LazyRecycleView(Context context) {
        super(context);
    }

    public LazyRecycleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LazyRecycleView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return false;
    }

}

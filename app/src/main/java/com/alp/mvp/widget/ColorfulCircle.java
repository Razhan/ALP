package com.alp.mvp.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.alp.mvp.R;

public final class ColorfulCircle extends View {

    private int color;
    private int radius;
    private Paint paint;


    public ColorfulCircle(Context context) {
        this(context, null);
    }

    public ColorfulCircle(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ColorfulCircle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ColorfulCircle);
        color = array.getColor(R.styleable.ColorfulCircle_fill_color,
                ContextCompat.getColor(context, R.color.colorPrimary));
        array.recycle();

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int finalSize = Math.min(widthSize, heightSize);

        setMeasuredDimension(finalSize, finalSize);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        radius = w / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setColor(color);
        canvas.drawCircle(radius, radius, radius, paint);
    }

    public void setColor(int color) {
        this.color = color;
        invalidate();
    }
}

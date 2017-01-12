package com.alp.mvp.widgete;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.alp.mvp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public final class ArrowedTextView extends FrameLayout {

    @BindView(R.id.overline)
    View overLine;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.underline)
    View underLine;
    @BindView(R.id.arrow)
    ImageView arrow;
    @BindView(R.id.icon)
    ImageView icon;

    public ArrowedTextView(Context context) {
        this(context, null);
    }

    public ArrowedTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ArrowedTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.view_arrowed_textview, this);
        ButterKnife.bind(this);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ArrowedTextView);

        String titleTest = array.getString(R.styleable.ArrowedTextView_title);
        boolean OverLion = array.getBoolean(R.styleable.ArrowedTextView_overline, true);
        boolean UnderLion = array.getBoolean(R.styleable.ArrowedTextView_underline, false);
        boolean showIcon = array.getBoolean(R.styleable.ArrowedTextView_show_icon, false);
        int icon = array.getResourceId(R.styleable.ArrowedTextView_icon, -1);

        array.recycle();

        setTitle(titleTest);
        showOverLine(OverLion);
        showUnderLine(UnderLion);
        showIcon(showIcon, icon);
    }

    public void setTitle(String text) {
        title.setText(text);
    }

    public void showOverLine(boolean flag) {
        if (flag) {
            overLine.setVisibility(VISIBLE);
        } else {
            overLine.setVisibility(GONE);
        }
    }

    public void showUnderLine(boolean flag) {
        if (flag) {
            underLine.setVisibility(VISIBLE);
        } else {
            underLine.setVisibility(GONE);
        }
    }

    public void showIcon(boolean flag, @DrawableRes int res) {
        if (flag) {
            icon.setImageResource(res);
        }
    }

    public void setListener(OnClickListener listener) {
        arrow.setOnClickListener(listener);
    }

}

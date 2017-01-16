package com.alp.mvp.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alp.mvp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public final class TitleBar extends FrameLayout implements View.OnClickListener {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.indicator)
    ImageView indicator;
    @BindView(R.id.bar_wrapper)
    RelativeLayout bar;

    private boolean isSelected;
    private Context context;
    private boolean isAnimating;
    private AnimatorListener animatorListener;
    private View subView;
    private boolean alwaysShow;

    public TitleBar(Context context) {
        this(context, null);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        this.context = context;
        animatorListener = new AnimatorListener();

        LayoutInflater.from(context).inflate(R.layout.view_title_bar, this);
        ButterKnife.bind(this);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.TitleBar);
        String titleText = array.getString(R.styleable.TitleBar_bar_title);
        alwaysShow = array.getBoolean(R.styleable.TitleBar_always_show, false);

        setTitle(titleText);
        showIndicator(alwaysShow);

        if (!alwaysShow) {
            setOnClickListener(this);
        }
    }

    private void setTitle(String text) {
        title.setText(text);
    }

    private void showIndicator(boolean flag) {
        if (!flag) {
            indicator.setVisibility(VISIBLE);
        } else {
            indicator.setVisibility(INVISIBLE);
        }
    }

    private void changeColor(boolean isExpanded) {
        if (isExpanded) {
            bar.setBackgroundColor(ContextCompat.getColor(context, com.alp.library.R.color.colorPrimary));
            title.setTextColor(Color.WHITE);
            indicator.setColorFilter(Color.WHITE);
        } else {
            bar.setBackgroundColor(Color.WHITE);
            title.setTextColor(Color.BLACK);
            indicator.setColorFilter(Color.BLACK);
        }
    }

    private void animateIndicator() {
        indicator.animate().rotationBy(180).setListener(animatorListener).start();
    }

    @Override
    public void onClick(View v) {
        if (isAnimating) {
            return;
        }

        isSelected = !isSelected;
        changeColor(isSelected);
        animateIndicator();

        if (subView != null && isSelected) {
            subView.setVisibility(VISIBLE);
            return;
        }

        if (subView != null && !isSelected) {
            subView.setVisibility(GONE);
            return;
        }
    }

    public void setSubView(View subView) {
        this.subView = subView;
        subView.setVisibility(GONE);
    }

    private class AnimatorListener extends AnimatorListenerAdapter {
        @Override
        public void onAnimationStart(Animator animation) {
            isAnimating = true;
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            isAnimating = false;
        }
    }

}

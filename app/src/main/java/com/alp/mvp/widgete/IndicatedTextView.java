package com.alp.mvp.widgete;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.alp.mvp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public final class IndicatedTextView extends FrameLayout {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.indicator)
    ImageView indicator;

    private boolean isChosen;
    private boolean isDescend;
    private Context context;

    private boolean isAnimating;
    private AnimatorListener animatorListener;
    private SortListener sortListener;

    public IndicatedTextView(Context context) {
        this(context, null);
    }

    public IndicatedTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IndicatedTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        this.isChosen = false;
        this.isDescend = true;
        this.context = context;
        animatorListener = new AnimatorListener();

        LayoutInflater.from(context).inflate(R.layout.view_indicated_text_view, this);
        ButterKnife.bind(this);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.IndicatedTextView);
        String titleText = array.getString(R.styleable.IndicatedTextView_name);
        array.recycle();

        setTitle(titleText);
    }

    public void setTitle(String str) {
        title.setText(str);
    }

    public void onChosen() {
        if (!isChosen) {
            isChosen = true;
            changeColor();
        } else if (!isAnimating) {
            isDescend = !isDescend;
            rotateIndicator();
        }

        if (sortListener != null) {
            sortListener.sortByKey(title.getText().toString(), isDescend);
        }
    }

    public void reset() {
        setColor(Color.BLACK);

        if (!isDescend) {
            rotateIndicator();
        }

        isChosen = false;
        isDescend = true;
    }

    private void changeColor() {
        if (isChosen) {
            setColor(ContextCompat.getColor(context, R.color.colorPrimary));
        } else {
            setColor(Color.BLACK);
        }
    }

    private void setColor(@ColorInt int color) {
        indicator.setColorFilter(color);
        title.setTextColor(color);
    }

    private void rotateIndicator() {
        indicator.animate().rotationBy(180).setListener(animatorListener).start();
    }

    public void setSortListener(SortListener sortListener) {
        this.sortListener = sortListener;
    }

    public interface SortListener {

        void sortByKey(String key, boolean isDescend);

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

package com.alp.mvp.games.ui;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.alp.library.base.ui.BaseActivity;
import com.alp.mvp.R;

import butterknife.BindView;
import butterknife.OnClick;

public class LiveGameActivity extends BaseActivity {

    private final static int ANIMATION_DURATION = 600;

    @BindView(R.id.score_left)
    TextView scoreLeft;
    @BindView(R.id.score_right)
    TextView scoreRight;
    @BindView(R.id.add_score_wrapper)
    FrameLayout addScoreWrapper;
    @BindView(R.id.game_detail_wrapper)
    FrameLayout detailWrapper;
    @BindView(R.id.game_card_wrapper)
    FrameLayout gameCardWrapper;

    private int margin;

    @Override
    public int getContentViewId() {
        return R.layout.activity_live_game;
    }

    @Override
    protected String setToolBarText() {
        return "LIVE Game";
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);

        margin = ((ViewGroup.MarginLayoutParams) detailWrapper.getLayoutParams()).topMargin * 2;

        addScoreWrapper.post(() -> addScoreWrapper.setTranslationY(addScoreWrapper.getHeight() + margin));

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.game_detail_wrapper, GameDetailFragment.newInstance())
                    .add(R.id.add_score_wrapper, AddScoreFragment.newInstance())
                    .commit();
        }
    }

    @OnClick({R.id.add_score_left, R.id.add_score_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_score_left:
                animateView(addScoreWrapper, detailWrapper, true);
                break;
            case R.id.add_score_right:
                animateView(addScoreWrapper, detailWrapper, false);
                break;
        }
    }

    private void animateView(View first, View second, boolean isGoUp) {
        int moveHeight;

        if (isGoUp) {
            moveHeight = -(first.getHeight() + margin);
        } else {
            moveHeight = first.getHeight() + margin;
        }

        first.animate()
                .translationYBy(moveHeight)
                .setInterpolator(new OvershootInterpolator())
                .setDuration(ANIMATION_DURATION)
                .start();

        second.animate()
                .translationYBy(moveHeight)
                .setInterpolator(new OvershootInterpolator())
                .setDuration(ANIMATION_DURATION)
                .start();
    }

}

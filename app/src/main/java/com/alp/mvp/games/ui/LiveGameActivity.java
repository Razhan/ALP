package com.alp.mvp.games.ui;

import android.app.FragmentManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.alp.library.base.ui.BaseActivity;
import com.alp.mvp.R;
import com.alp.mvp.games.data.model.Penalty;
import com.alp.mvp.games.data.model.Score;

import butterknife.BindView;
import butterknife.OnClick;

public class LiveGameActivity extends BaseActivity implements IAddOperationCallBack, SwipeRefreshLayout.OnRefreshListener {

    public final static int TYPE_ADD_SCORE = 0;
    public final static int TYPE_ADD_PENALTY = 1;
    private final static int ANIMATION_DURATION = 700;
    private final static int TEAM_LEFT = 0;
    private final static int TEAM_RIGHT = 1;

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
    @BindView(R.id.add_score_left)
    ImageView addScoreLeft;
    @BindView(R.id.add_score_right)
    ImageView addScoreRight;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;

    private int margin;
    private int selectedTeam = -1;

    private GameDetailFragment gameDetailFragment;
    private AddOperationFragment addOperationFragment;

    private FragmentManager manager;

    @Override
    public int getContentViewId() {
        return R.layout.activity_live_game;
    }

    @Override
    protected String setToolBarText() {
        return "LIVE Game";
    }

    @Override
    public void initData() {
        gameDetailFragment = GameDetailFragment.newInstance();
        manager = getFragmentManager();

        margin = ((ViewGroup.MarginLayoutParams) detailWrapper.getLayoutParams()).topMargin * 2;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);

        if (savedInstanceState == null) {
            manager.beginTransaction().add(R.id.game_detail_wrapper, gameDetailFragment).commit();
        }

        refresh.setOnRefreshListener(this);
        refresh.setColorSchemeColors(ContextCompat.getColor(this, R.color.colorPrimary));
        addScoreWrapper.post(() -> addScoreWrapper.setTranslationY(addScoreWrapper.getHeight() + margin));
    }

    @Override
    public void addPenalty(Penalty penalty) {
        gameDetailFragment.addPenalty("");
    }

    @Override
    public void addScore(Score score) {
        TextView textView;

        if (selectedTeam == TEAM_LEFT) {
            textView = scoreLeft;
        } else {
            textView = scoreRight;
        }

        int res = Integer.parseInt(textView.getText().toString()) + 1;
        textView.setText(String.valueOf(res));

        gameDetailFragment.addScore("");
    }

    @Override
    public void backToDetailPage() {
        selectedTeam = -1;
        toDetailPage();
    }

    @Override
    public void onBackPressed() {
        if (selectedTeam < 0) {
            super.onBackPressed();
        } else {
            addOperationFragment.onBack();
        }
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(() -> refresh.setRefreshing(false), 1500);
    }

    @OnClick({R.id.add_score_left, R.id.add_score_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_score_left:
                selectedTeam = TEAM_LEFT;
                break;
            case R.id.add_score_right:
                selectedTeam = TEAM_RIGHT;
                break;
        }

        showDialog();
    }

    private void showDialog() {
        final String[] operations = new String[]{"Add Score", "Add Penalty"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose Operation");
        builder.setPositiveButton("CANCEL", (dialog, which) -> dialog.dismiss());

        builder.setItems(operations, (dialog, which) -> {
            dialog.dismiss();

            addOperationFragment = AddOperationFragment.newInstance(which);
            manager.beginTransaction().replace(R.id.add_score_wrapper, addOperationFragment).commit();

            toAddScorePage();
        }).show();
    }

    private void toAddScorePage() {
        animateView(addScoreWrapper, detailWrapper, true);
        addScoreLeft.setVisibility(View.INVISIBLE);
        addScoreRight.setVisibility(View.INVISIBLE);
    }

    public void toDetailPage() {
        animateView(addScoreWrapper, detailWrapper, false);
        addScoreLeft.setVisibility(View.VISIBLE);
        addScoreRight.setVisibility(View.VISIBLE);
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

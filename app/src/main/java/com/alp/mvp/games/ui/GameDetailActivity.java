package com.alp.mvp.games.ui;

import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.TextView;

import com.alp.library.base.ui.BaseActivity;
import com.alp.mvp.R;
import com.alp.mvp.adapter.AttendPlayerAdapter;
import com.alp.mvp.adapter.GalleryAdapter;
import com.alp.mvp.adapter.PenaltyAdapter;
import com.alp.mvp.adapter.ScorePlayerAdapter;
import com.alp.mvp.adapter.SelectPlayerAdapter;
import com.alp.mvp.widget.TitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GameDetailActivity extends BaseActivity {

    @BindView(R.id.score_left)
    TextView scoreLeft;
    @BindView(R.id.score_right)
    TextView scoreRight;
    @BindView(R.id.title_bar_score)
    TitleBar scoreTitle;
    @BindView(R.id.score_list)
    RecyclerView scoreList;
    @BindView(R.id.title_bar_penalty)
    TitleBar penaltyTitle;
    @BindView(R.id.penalty_list)
    RecyclerView penaltyList;
    @BindView(R.id.title_bar_attend)
    TitleBar attendTitle;
    @BindView(R.id.attend_list)
    RecyclerView attendList;
    @BindView(R.id.gallery)
    RecyclerView gallery;
    @BindView(R.id.detail_wrapper)
    CardView detailWrapper;
    @BindView(R.id.add_score_wrapper)
    CardView addScoreWrapper;
    @BindView(R.id.select_player_list)
    RecyclerView selectPlayerList;

    private ScorePlayerAdapter scorePlayerAdapter;
    private AttendPlayerAdapter attendPlayerAdapter;
    private PenaltyAdapter penaltyAdapter;
    private GalleryAdapter galleryAdapter;
    private SelectPlayerAdapter selectPlayerAdapter;

    private int margin;

    @Override
    public int getContentViewId() {
        return R.layout.activity_game_detail;
    }

    @Override
    protected String setToolBarText() {
        return "LIVE Game";
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);

        initLists();

        scoreTitle.setSubView(scoreList);
        penaltyTitle.setSubView(penaltyList);
        attendTitle.setSubView(attendList);

        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) detailWrapper.getLayoutParams();
        margin = lp.topMargin * 2;
    }

    private void initLists() {
        scoreList.setNestedScrollingEnabled(false);
        penaltyList.setNestedScrollingEnabled(false);
        attendList.setNestedScrollingEnabled(false);

        List<String> list1 = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            list1.add("");
        }

        scorePlayerAdapter = new ScorePlayerAdapter(this, list1);

        scoreList.setLayoutManager(new LinearLayoutManager(this));
        scoreList.setAdapter(scorePlayerAdapter);

        List<String> list2 = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            list2.add("");
        }

        attendPlayerAdapter = new AttendPlayerAdapter(this, list2);

        attendList.setLayoutManager(new LinearLayoutManager(this));
        attendList.setAdapter(attendPlayerAdapter);

        List<String> list3 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list3.add("");
        }

        penaltyAdapter = new PenaltyAdapter(this, list3);

        penaltyList.setLayoutManager(new LinearLayoutManager(this));
        penaltyList.setAdapter(penaltyAdapter);

        List<String> list4 = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            list4.add("");
        }

        galleryAdapter = new GalleryAdapter(this, list4);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        gallery.setLayoutManager(linearLayoutManager);
        gallery.setAdapter(galleryAdapter);

        List<String> list5 = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            list5.add("");
        }

        selectPlayerAdapter = new SelectPlayerAdapter(this, list5);
        selectPlayerAdapter.setClickListener((view, pos, item) -> selectPlayerAdapter.updatePlayer(view));
        selectPlayerList.setLayoutManager(new LinearLayoutManager(this));
        selectPlayerList.setAdapter(selectPlayerAdapter);
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
        int duration = 600;

        if (isGoUp) {
            moveHeight = -(first.getHeight() + margin);
            first.setTranslationY(-moveHeight);
        } else {
            moveHeight = first.getHeight() + margin;
        }

        first.animate()
                .translationYBy(moveHeight)
                .setInterpolator(new OvershootInterpolator())
                .setDuration(duration)
                .start();

        second.animate()
                .translationYBy(moveHeight)
                .setInterpolator(new OvershootInterpolator())
                .setDuration(duration)
                .start();
    }

}

package com.alp.mvp.games.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alp.library.base.ui.BaseFragment;
import com.alp.mvp.R;
import com.alp.mvp.adapter.AttendPlayerAdapter;
import com.alp.mvp.adapter.GalleryAdapter;
import com.alp.mvp.adapter.PenaltyAdapter;
import com.alp.mvp.adapter.ScorePlayerAdapter;
import com.alp.mvp.widget.TitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class GameDetailFragment extends BaseFragment {

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

    private List<String> list1, list2, list3, list4, list5;
    private ScorePlayerAdapter scorePlayerAdapter;
    private AttendPlayerAdapter attendPlayerAdapter;
    private PenaltyAdapter penaltyAdapter;
    private GalleryAdapter galleryAdapter;

    public static Fragment newInstance() {
        return new GameDetailFragment();
    }

    @Override
    public int getContentViewId() {
        return R.layout.fragment_game_detail;
    }

    @Override
    public void initData() {
        list1 = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            list1.add("");
        }

        list2 = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            list2.add("");
        }

        list3 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list3.add("");
        }

        list4 = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            list4.add("");
        }

        list5 = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            list5.add("");
        }
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        scoreTitle.setSubView(scoreList);
        penaltyTitle.setSubView(penaltyList);
        attendTitle.setSubView(attendList);

        initLists();
    }

    private void initLists() {
        scoreList.setNestedScrollingEnabled(false);
        penaltyList.setNestedScrollingEnabled(false);
        attendList.setNestedScrollingEnabled(false);

        scorePlayerAdapter = new ScorePlayerAdapter(activity, list1);
        scoreList.setLayoutManager(new LinearLayoutManager(activity));
        scoreList.setAdapter(scorePlayerAdapter);

        attendPlayerAdapter = new AttendPlayerAdapter(activity, list2);
        attendList.setLayoutManager(new LinearLayoutManager(activity));
        attendList.setAdapter(attendPlayerAdapter);

        penaltyAdapter = new PenaltyAdapter(activity, list3);
        penaltyList.setLayoutManager(new LinearLayoutManager(activity));
        penaltyList.setAdapter(penaltyAdapter);

        galleryAdapter = new GalleryAdapter(activity, list4);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        gallery.setLayoutManager(linearLayoutManager);
        gallery.setAdapter(galleryAdapter);
    }

}

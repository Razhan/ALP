package com.alp.mvp.games.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alp.library.base.ui.BaseFragment;
import com.alp.mvp.R;
import com.alp.mvp.adapter.AttendPlayerAdapter;
import com.alp.mvp.adapter.GalleryAdapter;
import com.alp.mvp.adapter.PenaltyAdapter;
import com.alp.mvp.adapter.ScoreAdapter;
import com.alp.mvp.widget.TitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.alp.mvp.utils.MiscUtil.buildDeleteDialog;

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

    private List<String> list1, list2, list3, list4;
    private ScoreAdapter scoreAdapter;
    private AttendPlayerAdapter attendPlayerAdapter;
    private PenaltyAdapter penaltyAdapter;
    private GalleryAdapter galleryAdapter;

    public static GameDetailFragment newInstance() {
        return new GameDetailFragment();
    }

    @Override
    public int getContentViewId() {
        return R.layout.fragment_game_detail;
    }

    @Override
    public void initData() {
        list1 = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            list1.add("");
        }

        list2 = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            list2.add("");
        }

        list3 = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            list3.add("");
        }

        list4 = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            list4.add("");
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

        scoreAdapter = new ScoreAdapter(activity, list1);
        scoreAdapter.setLongClickListener((view, pos, item) -> buildDeleteDialog(activity,
                (dialog, which) -> scoreAdapter.remove(pos)));

        scoreList.setLayoutManager(new LinearLayoutManager(activity));
        scoreList.setAdapter(scoreAdapter);

        penaltyAdapter = new PenaltyAdapter(activity, list2);
        penaltyAdapter.setLongClickListener((view, pos, item) -> buildDeleteDialog(activity,
                (dialog, which) -> penaltyAdapter.remove(pos)));

        penaltyList.setLayoutManager(new LinearLayoutManager(activity));
        penaltyList.setAdapter(penaltyAdapter);

        attendPlayerAdapter = new AttendPlayerAdapter(activity, list3);
        attendPlayerAdapter.setAttendanceListener(null);
        attendList.setLayoutManager(new LinearLayoutManager(activity));
        attendList.setAdapter(attendPlayerAdapter);

        galleryAdapter = new GalleryAdapter(activity, list4);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        gallery.setLayoutManager(linearLayoutManager);
        gallery.setAdapter(galleryAdapter);
    }

    public void addScore(String str) {
        scoreAdapter.add(0, str);
    }

    public void addPenalty(String str) {
        penaltyAdapter.add(0, str);
    }

}

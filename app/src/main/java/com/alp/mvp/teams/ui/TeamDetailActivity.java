package com.alp.mvp.teams.ui;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alp.library.base.ui.BaseActivity;
import com.alp.mvp.R;
import com.alp.mvp.adapter.PlayerAdapter;
import com.alp.mvp.widgete.IndicatedTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class TeamDetailActivity extends BaseActivity {

    @BindView(R.id.player_list)
    RecyclerView playerList;
    @BindView(R.id.collapsing)
    CollapsingToolbarLayout collapsing;

    private PlayerAdapter adapter;
    private IndicatedTextView previousItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTranslucentStatusBar(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_team_detail;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);

        collapsing.setTitle("Team Name");

        initList();
    }

    private void initList() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            list.add("");
        }

        adapter = new PlayerAdapter(this, list);
        playerList.setLayoutManager(new LinearLayoutManager(this));
        playerList.setAdapter(adapter);
        playerList.setNestedScrollingEnabled(false);
    }

    @OnClick({R.id.position, R.id.gp, R.id.goal, R.id.assist, R.id.total})
    public void onClick(View view) {
        IndicatedTextView currentItem = (IndicatedTextView) view;

        if (previousItem != null && currentItem != previousItem) {
            previousItem.reset();
        }

        currentItem.onChosen();
        previousItem = currentItem;
    }

}

package com.alp.mvp.teams.ui;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.alp.library.base.ui.BaseMVPFragment;
import com.alp.mvp.ALPApplication;
import com.alp.mvp.R;
import com.alp.mvp.adapter.TeamAdapter;
import com.alp.mvp.di.components.DaggerTeamsComponent;
import com.alp.mvp.di.modules.ActivityModule;
import com.alp.mvp.teams.TeamsContract;
import com.alp.mvp.teams.TeamsPresenter;
import com.alp.mvp.widgete.IndicatedTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class CertainTeamsFragment extends BaseMVPFragment<TeamsPresenter> implements TeamsContract.View {

    @BindView(R.id.team_list)
    RecyclerView teamList;

    private IndicatedTextView previousItem;
    private TeamAdapter adapter;

    public static Fragment newInstance() {
        return new CertainTeamsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getPresenter().getTeams();
    }

    @Override
    public int getContentViewId() {
        return R.layout.fragment_certain_teams;
    }

    @Override
    protected void initializeInjector() {
        DaggerTeamsComponent.builder()
                .applicationComponent(((ALPApplication) activity.getApplication()).getApplicationComponent())
                .activityModule(new ActivityModule(activity))
                .build()
                .inject(this);
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        initList();
    }

    private void initList() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            list.add("");
        }

        adapter = new TeamAdapter(activity, list);

        adapter.setClickListener((view, pos, item) -> startActivity(new Intent(activity, TeamDetailActivity.class)));

        teamList.setLayoutManager(new LinearLayoutManager(getContext()));
        teamList.setAdapter(adapter);
    }

    @Override
    public void showTeams() {
        Log.d(TAG, "showTeams");
    }

    @OnClick({R.id.gp, R.id.w, R.id.l, R.id.t, R.id.pts})
    public void onClick(View view) {
        IndicatedTextView currentItem = (IndicatedTextView) view;

        if (previousItem != null && currentItem != previousItem) {
            previousItem.reset();
        }

        currentItem.onChosen();
        previousItem = currentItem;
    }

}

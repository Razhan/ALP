package com.alp.mvp.teams.ui;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.alp.library.base.ui.BaseMVPLoadFragment;
import com.alp.mvp.ALPApplication;
import com.alp.mvp.R;
import com.alp.mvp.adapter.TeamAdapter;
import com.alp.mvp.di.components.DaggerTeamsComponent;
import com.alp.mvp.di.modules.ActivityModule;
import com.alp.mvp.teams.TeamsContract;
import com.alp.mvp.teams.TeamsPresenter;
import com.alp.mvp.widget.IndicatedTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class CertainTeamsFragment extends BaseMVPLoadFragment<String, TeamsPresenter>
        implements TeamsContract.View {

    @BindView(R.id.content_view)
    RecyclerView teamList;

    private IndicatedTextView previousItem;
    private TeamAdapter adapter;

    public static Fragment newInstance() {
        return new CertainTeamsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "onCreate");
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loadData(false);
        Log.d(TAG, "onViewCreated");

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

    @Override
    public void loadData(boolean pullToRefresh) {
        getPresenter().getTeams();
    }

    private void initList() {
        adapter = new TeamAdapter(activity, null);
        adapter.setClickListener((view, pos, item) -> startActivity(new Intent(activity, TeamDetailActivity.class)));

        teamList.setLayoutManager(new LinearLayoutManager(getContext()));
        teamList.setAdapter(adapter);
    }

    @Override
    public void showContent(String data) {
        super.showContent(data);

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            list.add("");
        }

        adapter.set(list);
    }

    @OnClick({R.id.team_header_gp, R.id.team_header_w, R.id.team_header_l, R.id.team_header_t, R.id.team_header_pts})
    public void onClick(View view) {
        IndicatedTextView currentItem = (IndicatedTextView) view;

        if (previousItem != null && currentItem != previousItem) {
            previousItem.reset();
        }

        currentItem.onChosen();
        previousItem = currentItem;
    }

}

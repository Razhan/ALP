package com.alp.mvp.players.ui;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alp.library.base.ui.BaseMVPLoadFragment;
import com.alp.mvp.ALPApplication;
import com.alp.mvp.R;
import com.alp.mvp.adapter.PlayerAdapter;
import com.alp.mvp.di.components.DaggerPlayersComponent;
import com.alp.mvp.di.modules.ActivityModule;
import com.alp.mvp.players.PlayersContract;
import com.alp.mvp.players.PlayersPresenter;
import com.alp.mvp.widget.IndicatedTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class CertainPlayersFragment extends BaseMVPLoadFragment<String, PlayersPresenter>
        implements PlayersContract.View {

    @BindView(R.id.content_view)
    RecyclerView playerList;

    private IndicatedTextView previousItem;
    private PlayerAdapter adapter;

    public static Fragment newInstance() {
        return new CertainPlayersFragment();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loadData(false);
    }

    @Override
    public int getContentViewId() {
        return R.layout.fragment_certain_players;
    }

    @Override
    protected void initializeInjector() {
        DaggerPlayersComponent.builder()
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
        getPresenter().getPlayers();
    }

    private void initList() {
        adapter = new PlayerAdapter(activity, null);

        adapter.setClickListener((view, pos, item) -> startActivity(new Intent(activity, PlayerDetailActivity.class)));

        playerList.setLayoutManager(new LinearLayoutManager(getContext()));
        playerList.setAdapter(adapter);
    }

    @Override
    public void showContent(String data) {
        super.showContent(data);

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 28; i++) {
            list.add("");
        }

        adapter.set(list);
    }

    @OnClick({R.id.player_header_position, R.id.player_header_gp, R.id.player_header_goal,
            R.id.player_header_assist, R.id.player_header_total})
    public void onClick(View view) {
        IndicatedTextView currentItem = (IndicatedTextView) view;

        if (previousItem != null && currentItem != previousItem) {
            previousItem.reset();
        }

        currentItem.onChosen();
        previousItem = currentItem;
    }

}

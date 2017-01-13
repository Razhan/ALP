package com.alp.mvp.games.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.alp.library.base.ui.BaseMVPFragment;
import com.alp.mvp.ALPApplication;
import com.alp.mvp.R;
import com.alp.mvp.adapter.GameAdapter;
import com.alp.mvp.di.components.DaggerGamesComponent;
import com.alp.mvp.di.modules.ActivityModule;
import com.alp.mvp.games.GamesContract;
import com.alp.mvp.games.GamesPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class CertainGamesFragment extends BaseMVPFragment<GamesPresenter> implements GamesContract.View {

    @BindView(R.id.game_list)
    RecyclerView gameList;

    private GameAdapter adapter;

    public static Fragment newInstance() {
        return new CertainGamesFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getPresenter().getGames();
    }

    @Override
    public int getContentViewId() {
        return R.layout.fragment_certain_games;
    }

    @Override
    protected void initializeInjector() {
        DaggerGamesComponent.builder()
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

        adapter = new GameAdapter(activity, list);
        gameList.setLayoutManager(new LinearLayoutManager(getContext()));
        gameList.setAdapter(adapter);
    }

    @Override
    public void showGames() {
        Log.d(TAG, "showGames");
    }

}

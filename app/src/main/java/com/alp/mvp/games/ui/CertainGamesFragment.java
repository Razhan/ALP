package com.alp.mvp.games.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.alp.library.base.ui.BaseMVPFragment;
import com.alp.mvp.ALPApplication;
import com.alp.mvp.R;
import com.alp.mvp.adapter.GameListAdapter;
import com.alp.mvp.di.components.DaggerGamesComponent;
import com.alp.mvp.di.modules.ActivityModule;
import com.alp.mvp.games.GamesContract;
import com.alp.mvp.games.GamesPresenter;
import com.alp.mvp.widget.LazyRecycleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class CertainGamesFragment extends BaseMVPFragment<GamesPresenter> implements GamesContract.View {

    @BindView(R.id.game_list)
    LazyRecycleView gameList;
    @BindView(R.id.left_arrow)
    ImageView leftArrow;
    @BindView(R.id.right_arrow)
    ImageView rightArrow;

    private GameListAdapter adapter;
    private int currentPos = 0;

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
        updateArrow();
    }

    private void updateArrow() {
        if (adapter == null || adapter.getItemCount() == 0) {
            leftArrow.setColorFilter(ContextCompat.getColor(activity, R.color.grey));
            rightArrow.setColorFilter(ContextCompat.getColor(activity, R.color.grey));
            return;
        }

        if (currentPos <= 0) {
            leftArrow.setColorFilter(ContextCompat.getColor(activity, R.color.grey));
            rightArrow.setColorFilter(ContextCompat.getColor(activity, R.color.colorPrimary));
            return;
        }

        if (currentPos >=  adapter.getItemCount() - 1) {
            leftArrow.setColorFilter(ContextCompat.getColor(activity, R.color.colorPrimary));
            rightArrow.setColorFilter(ContextCompat.getColor(activity, R.color.grey));
            return;
        }

        if (currentPos > 0 && currentPos < adapter.getItemCount() - 1) {
            rightArrow.setColorFilter(ContextCompat.getColor(activity, R.color.colorPrimary));
            leftArrow.setColorFilter(ContextCompat.getColor(activity, R.color.colorPrimary));
            return;
        }
    }

    private void initList() {
        List<String> list1 = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            list1.add("111");
        }

        List<String> list2 = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            list2.add("222");
        }

        List<String> list3 = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            list3.add("333");
        }

        List<List<String>> lists = new ArrayList<>();

        lists.add(list1);
        lists.add(list2);
        lists.add(list3);

        adapter = new GameListAdapter(activity, lists);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity) {
            @Override
            public boolean canScrollHorizontally() {
                return false;
            }
        };

        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        gameList.setLayoutManager(linearLayoutManager);
        gameList.setAdapter(adapter);
    }

    @Override
    public void showGames() {
        Log.d(TAG, "showGames");
    }

    @OnClick({R.id.left_arrow, R.id.right_arrow})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.left_arrow:
                if (currentPos > 0) {
                    gameList.scrollToPosition(--currentPos);
                    updateArrow();
                }
                break;
            case R.id.right_arrow:
                if (currentPos < adapter.getItemCount() - 1) {
                    gameList.scrollToPosition(++currentPos);
                    updateArrow();
                }
                break;
        }
    }

}

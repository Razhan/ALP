package com.alp.mvp.games.ui;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.alp.library.base.ui.BaseMVPLoadFragment;
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
import butterknife.OnClick;

public class CertainGamesFragment extends BaseMVPLoadFragment<String, GamesPresenter>
        implements GamesContract.View {

    @BindView(R.id.content_view)
    RecyclerView gameList;
    @BindView(R.id.left_arrow)
    ImageView leftArrow;
    @BindView(R.id.right_arrow)
    ImageView rightArrow;

    private GameAdapter adapter;
    private int currentPos = 0;
    private List<List<String>> lists;

    public static Fragment newInstance() {
        return new CertainGamesFragment();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loadData(false);
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
        if (lists == null || lists.size() == 0 || adapter == null) {
            leftArrow.setColorFilter(ContextCompat.getColor(activity, R.color.grey));
            rightArrow.setColorFilter(ContextCompat.getColor(activity, R.color.grey));
            return;
        }

        if (currentPos <= 0) {
            leftArrow.setColorFilter(ContextCompat.getColor(activity, R.color.grey));
            rightArrow.setColorFilter(ContextCompat.getColor(activity, R.color.colorPrimary));
            return;
        }

        if (currentPos >= lists.size() - 1) {
            leftArrow.setColorFilter(ContextCompat.getColor(activity, R.color.colorPrimary));
            rightArrow.setColorFilter(ContextCompat.getColor(activity, R.color.grey));
            return;
        }

        if (currentPos > 0 && currentPos < lists.size() - 1) {
            rightArrow.setColorFilter(ContextCompat.getColor(activity, R.color.colorPrimary));
            leftArrow.setColorFilter(ContextCompat.getColor(activity, R.color.colorPrimary));
        }
    }

    private void initList() {
        adapter = new GameAdapter(activity, null);
        adapter.setClickListener((view, pos, item) -> startActivity(new Intent(activity, LiveGameActivity.class)));
        adapter.setAttendanceListener(null);

        gameList.setLayoutManager(new LinearLayoutManager(activity));
        gameList.setAdapter(adapter);
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        getPresenter().getGames();
    }

    @Override
    public void showContent(String data) {
        super.showContent(data);

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

        lists = new ArrayList<>();

        lists.add(list1);
        lists.add(list2);
        lists.add(list3);

        adapter.set(lists.get(0));
        updateArrow();
    }

    @OnClick({R.id.left_arrow, R.id.right_arrow})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.left_arrow:
                if (lists != null && currentPos > 0) {
                    adapter.set(lists.get(--currentPos));
                    updateArrow();
                }
                break;
            case R.id.right_arrow:
                if (lists != null && currentPos < lists.size() - 1) {
                    adapter.set(lists.get(++currentPos));
                    updateArrow();
                }
                break;
        }
    }

}

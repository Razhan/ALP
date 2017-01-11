package com.alp.mvp.games.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;

import com.alp.library.base.ui.BaseMVPFragment;
import com.alp.mvp.ALPApplication;
import com.alp.mvp.R;
import com.alp.mvp.di.components.DaggerGamesComponent;
import com.alp.mvp.di.modules.ActivityModule;
import com.alp.mvp.games.GamesContract;
import com.alp.mvp.games.GamesPresenter;

public class MyGamesFragment extends BaseMVPFragment<GamesPresenter> implements GamesContract.View {

    public static Fragment newInstance() {
        return new MyGamesFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getPresenter().getGames();
    }

    @Override
    public int getContentViewId() {
        return R.layout.fragment_page;
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
    public void showGames() {
        Log.d(TAG, "showGames");
    }

}

package com.alp.mvp.games;

import com.alp.library.base.ui.BaseMVPFragment;
import com.alp.mvp.ALPApplication;
import com.alp.mvp.di.components.DaggerGamesComponent;
import com.alp.mvp.di.modules.ActivityModule;

public class GamesFragment extends BaseMVPFragment<GamesPresenter> implements GamesContract.View {

    @Override
    public int getContentViewId() {
        return 0;
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

    }

}

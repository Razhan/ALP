package com.alp.mvp.players.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.alp.library.base.ui.BaseMVPFragment;
import com.alp.mvp.ALPApplication;
import com.alp.mvp.R;
import com.alp.mvp.di.components.DaggerPlayersComponent;
import com.alp.mvp.di.modules.ActivityModule;
import com.alp.mvp.players.PlayersContract;
import com.alp.mvp.players.PlayersPresenter;
import com.alp.mvp.widgete.IndicatedTextView;

import butterknife.OnClick;

public class CertainPlayersFragment extends BaseMVPFragment<PlayersPresenter> implements PlayersContract.View {

    private IndicatedTextView previousItem;

    public static Fragment newInstance() {
        return new CertainPlayersFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getPresenter().getPlayers();
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
    public void showPlayers() {
        Log.d(TAG, "showPlayers");
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

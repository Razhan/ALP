package com.alp.mvp.teams.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.alp.library.base.ui.BaseMVPFragment;
import com.alp.mvp.ALPApplication;
import com.alp.mvp.R;
import com.alp.mvp.di.components.DaggerTeamsComponent;
import com.alp.mvp.di.modules.ActivityModule;
import com.alp.mvp.teams.TeamsContract;
import com.alp.mvp.teams.TeamsPresenter;
import com.alp.mvp.widgete.IndicatedTextView;

import butterknife.OnClick;

public class CertainTeamsFragment extends BaseMVPFragment<TeamsPresenter> implements TeamsContract.View {

    private IndicatedTextView previousItem;

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

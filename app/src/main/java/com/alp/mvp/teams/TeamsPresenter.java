package com.alp.mvp.teams;

import android.app.Activity;
import android.util.Log;

import com.alp.library.presenter.BasePresenter;
import com.alp.library.usecase.UseCase;
import com.alp.mvp.teams.domain.GetTeams;

import javax.inject.Inject;
import javax.inject.Named;

public final class TeamsPresenter extends BasePresenter<TeamsContract.View> implements TeamsContract.Presenter {

    private final UseCase getTeams;

    @Inject
    TeamsPresenter(@Named(GetTeams.NAME) UseCase useCase, Activity activity) {
        super(activity);
        this.getTeams = useCase;
    }

    @Override
    public void getTeams() {
        Log.d("getGames", "getGames");
        getView().showTeams();
    }

    @Override
    public void unSubscribe() {
        getTeams.dispose();
    }

}
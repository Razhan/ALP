package com.alp.mvp.league;

import android.app.Activity;
import android.util.Log;

import com.alp.library.presenter.BasePresenter;
import com.alp.library.usecase.UseCase;
import com.alp.mvp.league.domain.GetLeague;

import javax.inject.Inject;
import javax.inject.Named;

public final class LeaguePresenter extends BasePresenter<LeagueContract.View> implements LeagueContract.Presenter {

    private final UseCase getLeague;

    @Inject
    LeaguePresenter(@Named(GetLeague.NAME) UseCase useCase, Activity activity) {
        super(activity);
        this.getLeague = useCase;
    }

    @Override
    public void getLeague() {
        Log.d("getLeague", "getLeague");
        getView().showLeague();
    }

    @Override
    public void unSubscribe() {
        getLeague.dispose();
    }

}
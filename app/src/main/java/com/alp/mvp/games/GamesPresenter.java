package com.alp.mvp.games;

import android.app.Activity;
import android.util.Log;

import com.alp.library.presenter.BasePresenter;
import com.alp.library.usecase.UseCase;
import com.alp.mvp.games.domain.GetGames;

import javax.inject.Inject;
import javax.inject.Named;

public final class GamesPresenter extends BasePresenter<GamesContract.View> implements GamesContract.Presenter {

    private final UseCase getGames;

    @Inject
    GamesPresenter(@Named(GetGames.NAME) UseCase useCase, Activity activity) {
        super(activity);
        this.getGames = useCase;
    }

    @Override
    public void getGames() {
        Log.d("getGames", "getGames");
        getView().showGames();
    }

    @Override
    public void unSubscribe() {
        getGames.dispose();
    }

}
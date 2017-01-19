package com.alp.mvp.players;

import android.app.Activity;
import android.os.Handler;

import com.alp.library.presenter.BasePresenter;
import com.alp.library.usecase.UseCase;
import com.alp.mvp.players.domain.GetPlayers;

import javax.inject.Inject;
import javax.inject.Named;

public final class PlayersPresenter extends BasePresenter<PlayersContract.View> implements PlayersContract.Presenter {

    private final UseCase getPlayers;

    @Inject
    PlayersPresenter(@Named(GetPlayers.NAME) UseCase useCase, Activity activity) {
        super(activity);
        this.getPlayers = useCase;
    }

    @Override
    public void getPlayers() {
        getView().showLoading(false);

        new Handler().postDelayed(() -> getView().showContent(""), 1000);
    }

    @Override
    public void unSubscribe() {
        getPlayers.dispose();
    }

}
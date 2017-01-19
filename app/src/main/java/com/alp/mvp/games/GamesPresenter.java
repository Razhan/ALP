package com.alp.mvp.games;

import android.app.Activity;
import android.os.Handler;

import com.alp.library.presenter.BasePresenter;
import com.alp.library.usecase.UseCase;
import com.alp.mvp.games.domain.GetGames;

import javax.inject.Inject;
import javax.inject.Named;

public final class GamesPresenter extends BasePresenter<GamesContract.View> implements GamesContract.Presenter {

    private final UseCase getGames;

    private int count;

    @Inject
    GamesPresenter(@Named(GetGames.NAME) UseCase useCase, Activity activity) {
        super(activity);
        this.getGames = useCase;
    }

    @Override
    public void getGames() {
        getView().showLoading(false);

        if (count > 0) {
            new Handler().postDelayed(() -> getView().showContent(""), 1000);
        } else {
            new Handler().postDelayed(() -> getView().showError("An error has occurred! click here to retry", false), 1000);
            count++;
        }
    }

    @Override
    public void unSubscribe() {
        getGames.dispose();
    }

}
package com.alp.mvp.games.data;

import com.alp.mvp.games.data.model.GamesResponse;
import com.alp.mvp.games.data.remote.GamesAPIService;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GamesRepositoryImp implements IGamesRepository {

    private final GamesAPIService remoteService;

    @Inject
    public GamesRepositoryImp(GamesAPIService remoteService) {
        this.remoteService = remoteService;
    }

    @Override
    public Observable<GamesResponse> getTestString() {
        return remoteService.getStart();
    }


}


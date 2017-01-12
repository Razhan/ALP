package com.alp.mvp.players.data;

import com.alp.mvp.players.data.model.PlayersResponse;
import com.alp.mvp.players.data.remote.PlayersAPIService;

import javax.inject.Inject;

import io.reactivex.Observable;

public class PlayersRepositoryImp implements IPlayersRepository {

    private final PlayersAPIService remoteService;

    @Inject
    PlayersRepositoryImp(PlayersAPIService remoteService) {
        this.remoteService = remoteService;
    }

    @Override
    public Observable<PlayersResponse> getTestString() {
        return remoteService.getStart();
    }


}


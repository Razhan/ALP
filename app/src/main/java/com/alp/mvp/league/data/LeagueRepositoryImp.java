package com.alp.mvp.league.data;

import com.alp.mvp.league.data.model.LeagueResponse;
import com.alp.mvp.league.data.remote.LeagueAPIService;

import javax.inject.Inject;

import io.reactivex.Observable;

public class LeagueRepositoryImp implements ILeagueRepository {

    private final LeagueAPIService remoteService;

    @Inject
    LeagueRepositoryImp(LeagueAPIService remoteService) {
        this.remoteService = remoteService;
    }

    @Override
    public Observable<LeagueResponse> getTestString() {
        return remoteService.getStart();
    }


}


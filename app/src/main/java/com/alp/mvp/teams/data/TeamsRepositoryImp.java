package com.alp.mvp.teams.data;

import com.alp.mvp.teams.data.model.TeamsResponse;
import com.alp.mvp.teams.data.remote.TeamsAPIService;

import javax.inject.Inject;

import io.reactivex.Observable;

public class TeamsRepositoryImp implements ITeamsRepository {

    private final TeamsAPIService remoteService;

    @Inject
    TeamsRepositoryImp(TeamsAPIService remoteService) {
        this.remoteService = remoteService;
    }

    @Override
    public Observable<TeamsResponse> getTestString() {
        return remoteService.getStart();
    }


}


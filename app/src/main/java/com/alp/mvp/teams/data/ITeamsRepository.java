package com.alp.mvp.teams.data;

import com.alp.mvp.teams.data.model.TeamsResponse;

import io.reactivex.Observable;

public interface ITeamsRepository {

    Observable<TeamsResponse> getTestString();

}

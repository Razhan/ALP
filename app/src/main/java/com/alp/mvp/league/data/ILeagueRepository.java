package com.alp.mvp.league.data;

import com.alp.mvp.league.data.model.LeagueResponse;

import io.reactivex.Observable;

public interface ILeagueRepository {

    Observable<LeagueResponse> getTestString();

}

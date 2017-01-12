package com.alp.mvp.league.data.remote;

import com.alp.mvp.league.data.model.LeagueResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

interface ILeagueAPI {

    @GET("/api/onthemove/bootstrap")
    Observable<LeagueResponse> bootstrap();

}

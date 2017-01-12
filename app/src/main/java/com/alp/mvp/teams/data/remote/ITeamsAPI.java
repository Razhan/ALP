package com.alp.mvp.teams.data.remote;

import com.alp.mvp.teams.data.model.TeamsResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

interface ITeamsAPI {

    @GET("/api/onthemove/bootstrap")
    Observable<TeamsResponse> bootstrap();

}

package com.alp.mvp.games.data.remote;

import com.alp.mvp.games.data.model.GamesResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

interface IGamesAPI {

    @GET("/api/onthemove/bootstrap")
    Observable<GamesResponse> bootstrap();

}

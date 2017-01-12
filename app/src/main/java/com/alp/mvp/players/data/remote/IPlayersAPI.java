package com.alp.mvp.players.data.remote;

import com.alp.mvp.players.data.model.PlayersResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

interface IPlayersAPI {

    @GET("/api/onthemove/bootstrap")
    Observable<PlayersResponse> bootstrap();

}

package com.alp.mvp.games.data;

import com.alp.mvp.games.data.model.GamesResponse;

import io.reactivex.Observable;

public interface IGamesRepository {

    Observable<GamesResponse> getTestString();

}

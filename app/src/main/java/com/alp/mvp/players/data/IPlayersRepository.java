package com.alp.mvp.players.data;

import com.alp.mvp.players.data.model.PlayersResponse;

import io.reactivex.Observable;

public interface IPlayersRepository {

    Observable<PlayersResponse> getTestString();

}

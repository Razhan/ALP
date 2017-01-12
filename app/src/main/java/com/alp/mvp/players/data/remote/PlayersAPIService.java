package com.alp.mvp.players.data.remote;

import com.alp.mvp.players.data.model.PlayersResponse;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import javax.inject.Inject;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PlayersAPIService {

    private final IPlayersAPI testAPI;

    @Inject
    PlayersAPIService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://b2cglobaluat.englishtown.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        testAPI = retrofit.create(IPlayersAPI.class);
    }

    public Observable<PlayersResponse> getStart() {
        return testAPI.bootstrap();
    }

}

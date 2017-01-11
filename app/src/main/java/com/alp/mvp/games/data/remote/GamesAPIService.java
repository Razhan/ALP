package com.alp.mvp.games.data.remote;

import com.alp.mvp.games.data.model.GamesResponse;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import javax.inject.Inject;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GamesAPIService {

    private final IGamesAPI testAPI;

    @Inject
    GamesAPIService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://b2cglobaluat.englishtown.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        testAPI = retrofit.create(IGamesAPI.class);
    }

    public Observable<GamesResponse> getStart() {
        return testAPI.bootstrap();
    }

}

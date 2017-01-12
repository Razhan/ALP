package com.alp.mvp.league.data.remote;

import com.alp.mvp.league.data.model.LeagueResponse;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import javax.inject.Inject;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LeagueAPIService {

    private final ILeagueAPI testAPI;

    @Inject
    LeagueAPIService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://b2cglobaluat.englishtown.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        testAPI = retrofit.create(ILeagueAPI.class);
    }

    public Observable<LeagueResponse> getStart() {
        return testAPI.bootstrap();
    }

}

package com.alp.mvp.teams.data.remote;

import com.alp.mvp.teams.data.model.TeamsResponse;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import javax.inject.Inject;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TeamsAPIService {

    private final ITeamsAPI testAPI;

    @Inject
    TeamsAPIService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://b2cglobaluat.englishtown.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        testAPI = retrofit.create(ITeamsAPI.class);
    }

    public Observable<TeamsResponse> getStart() {
        return testAPI.bootstrap();
    }

}

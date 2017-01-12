package com.alp.mvp.di.modules;

import com.alp.library.di.PerActivity;
import com.alp.library.usecase.UseCase;
import com.alp.mvp.league.data.ILeagueRepository;
import com.alp.mvp.league.data.LeagueRepositoryImp;
import com.alp.mvp.league.domain.GetLeague;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class LeagueModule {

    @Provides
    @PerActivity
    @Named(GetLeague.NAME)
    UseCase provideGetLeagueUseCase(GetLeague testUseCase) {
        return testUseCase;
    }

    @Provides
    @PerActivity
    ILeagueRepository provideLeagueRepository(LeagueRepositoryImp testRepository) {
        return testRepository;
    }

}
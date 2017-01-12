package com.alp.mvp.di.modules;

import com.alp.library.di.PerActivity;
import com.alp.library.usecase.UseCase;
import com.alp.mvp.teams.data.ITeamsRepository;
import com.alp.mvp.teams.data.TeamsRepositoryImp;
import com.alp.mvp.teams.domain.GetTeams;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class TeamsModule {

    @Provides
    @PerActivity
    @Named(GetTeams.NAME)
    UseCase provideGetTeamsUseCase(GetTeams testUseCase) {
        return testUseCase;
    }

    @Provides
    @PerActivity
    ITeamsRepository provideGamesRepository(TeamsRepositoryImp testRepository) {
        return testRepository;
    }

}
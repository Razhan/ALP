package com.alp.mvp.di.modules;

import com.alp.library.di.PerActivity;
import com.alp.library.usecase.UseCase;
import com.alp.mvp.games.data.GamesRepositoryImp;
import com.alp.mvp.games.data.IGamesRepository;
import com.alp.mvp.games.domain.GetGames;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class GamesModule {

    @Provides
    @PerActivity
    @Named(GetGames.NAME)
    UseCase provideGetGamesUseCase(GetGames testUseCase) {
        return testUseCase;
    }

    @Provides
    @PerActivity
    IGamesRepository provideGamesRepository(GamesRepositoryImp testRepository) {
        return testRepository;
    }

}

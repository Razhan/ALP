package com.alp.mvp.di.modules;

import com.alp.library.di.PerActivity;
import com.alp.library.usecase.UseCase;
import com.alp.mvp.players.data.IPlayersRepository;
import com.alp.mvp.players.data.PlayersRepositoryImp;
import com.alp.mvp.players.domain.GetPlayers;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class PlayersModule {

    @Provides
    @PerActivity
    @Named(GetPlayers.NAME)
    UseCase provideGetPlayersUseCase(GetPlayers testUseCase) {
        return testUseCase;
    }

    @Provides
    @PerActivity
    IPlayersRepository provideGamesRepository(PlayersRepositoryImp testRepository) {
        return testRepository;
    }

}
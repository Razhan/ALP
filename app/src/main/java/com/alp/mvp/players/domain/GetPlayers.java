package com.alp.mvp.players.domain;

import com.alp.library.di.JobThread;
import com.alp.library.di.UIThread;
import com.alp.library.usecase.UseCase;
import com.alp.library.usecase.executor.ExecutionThread;
import com.alp.mvp.players.data.IPlayersRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

public final class GetPlayers extends UseCase {

    public static final String NAME = "GetPlayers";

    @Inject
    IPlayersRepository repository;

    @Inject
    GetPlayers(@JobThread ExecutionThread executionThread, @UIThread ExecutionThread postExecutionThread) {
        super(executionThread, postExecutionThread);
    }

    @Override
    protected Observable buildUseCaseObservable(Object... params) {
        return Observable.empty();
    }

}
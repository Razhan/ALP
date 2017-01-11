package com.alp.mvp.games.domain;

import com.alp.library.di.JobThread;
import com.alp.library.di.UIThread;
import com.alp.library.usecase.UseCase;
import com.alp.library.usecase.executor.ExecutionThread;
import com.alp.mvp.games.data.IGamesRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

public final class GetGames extends UseCase {

    public static final String NAME = "GetGames";

    @Inject
    IGamesRepository repository;

    @Inject
    public GetGames(@JobThread ExecutionThread executionThread, @UIThread ExecutionThread postExecutionThread) {
        super(executionThread, postExecutionThread);
    }

    @Override
    protected Observable buildUseCaseObservable(Object... params) {
        return Observable.empty();
    }

}

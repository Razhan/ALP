package com.alp.mvp.league.domain;

import com.alp.library.di.JobThread;
import com.alp.library.di.UIThread;
import com.alp.library.usecase.UseCase;
import com.alp.library.usecase.executor.ExecutionThread;
import com.alp.mvp.league.data.ILeagueRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

public final class GetLeague extends UseCase {

    public static final String NAME = "GetLeague";

    @Inject
    ILeagueRepository repository;

    @Inject
    GetLeague(@JobThread ExecutionThread executionThread, @UIThread ExecutionThread postExecutionThread) {
        super(executionThread, postExecutionThread);
    }

    @Override
    protected Observable buildUseCaseObservable(Object... params) {
        return Observable.empty();
    }

}

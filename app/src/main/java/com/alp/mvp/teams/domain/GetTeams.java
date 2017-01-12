package com.alp.mvp.teams.domain;

import com.alp.library.di.JobThread;
import com.alp.library.di.UIThread;
import com.alp.library.usecase.UseCase;
import com.alp.library.usecase.executor.ExecutionThread;
import com.alp.mvp.teams.data.ITeamsRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

public final class GetTeams extends UseCase {

    public static final String NAME = "GetGames";

    @Inject
    ITeamsRepository repository;

    @Inject
    GetTeams(@JobThread ExecutionThread executionThread, @UIThread ExecutionThread postExecutionThread) {
        super(executionThread, postExecutionThread);
    }

    @Override
    protected Observable buildUseCaseObservable(Object... params) {
        return Observable.empty();
    }

}

package com.alp.mvp.di.modules;

import android.content.Context;

import com.alp.library.di.JobThread;
import com.alp.library.di.UIThread;
import com.alp.library.exception.IErrorHandler;
import com.alp.library.usecase.executor.ExecutionThread;
import com.alp.library.usecase.executor.JobScheduler;
import com.alp.library.usecase.executor.UIScheduler;
import com.alp.mvp.ALPApplication;
import com.alp.mvp.ErrorHandler;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private final ALPApplication application;

    public ApplicationModule(ALPApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.application;
    }

    @Provides
    @Singleton
    @JobThread
    ExecutionThread provideThreadExecutor(JobScheduler jobThread) {
        return jobThread;
    }

    @Provides
    @Singleton
    @UIThread
    ExecutionThread providePostExecutionThread(UIScheduler uiThread) {
        return uiThread;
    }

    @Provides
    @Singleton
    IErrorHandler provideErrorHandler(ErrorHandler handler) {
        return handler;
    }

}

package com.alp.mvp;

import android.app.Application;

import com.alp.mvp.di.components.ApplicationComponent;
import com.alp.mvp.di.components.DaggerApplicationComponent;
import com.alp.mvp.di.modules.ApplicationModule;

public class ALPApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        this.initializeInjector();
    }

    private void initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }

}
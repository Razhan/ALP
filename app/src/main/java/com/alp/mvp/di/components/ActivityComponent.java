package com.alp.mvp.di.components;

import android.app.Activity;

import com.alp.library.di.PerActivity;
import com.alp.mvp.di.modules.ActivityModule;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
interface ActivityComponent {

    Activity activity();

}
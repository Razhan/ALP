package com.alp.mvp.di.components;

import com.alp.library.di.PerActivity;
import com.alp.mvp.di.modules.ActivityModule;
import com.alp.mvp.di.modules.TeamsModule;
import com.alp.mvp.teams.ui.CertainTeamsFragment;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, TeamsModule.class})
public interface TeamsComponent extends ActivityComponent {

    void inject(CertainTeamsFragment fragment);

}

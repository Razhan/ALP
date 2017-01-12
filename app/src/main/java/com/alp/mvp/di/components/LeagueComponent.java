package com.alp.mvp.di.components;

import com.alp.library.di.PerActivity;
import com.alp.mvp.di.modules.ActivityModule;
import com.alp.mvp.di.modules.LeagueModule;
import com.alp.mvp.league.ui.LeagueFragment;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, LeagueModule.class})
public interface LeagueComponent extends ActivityComponent {

    void inject(LeagueFragment fragment);

}

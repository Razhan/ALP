package com.alp.mvp.di.components;

import com.alp.library.di.PerActivity;
import com.alp.mvp.di.modules.ActivityModule;
import com.alp.mvp.di.modules.GamesModule;
import com.alp.mvp.games.ui.AllGamesFragment;
import com.alp.mvp.games.ui.MyGamesFragment;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, GamesModule.class})
interface GamesComponent extends ActivityComponent {

    void inject(MyGamesFragment fragment);

    void inject(AllGamesFragment fragment);

}
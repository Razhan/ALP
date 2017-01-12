package com.alp.mvp.di.components;

import com.alp.library.di.PerActivity;
import com.alp.mvp.di.modules.ActivityModule;
import com.alp.mvp.di.modules.PlayersModule;
import com.alp.mvp.players.ui.CertainPlayersFragment;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, PlayersModule.class})
public interface PlayersComponent extends ActivityComponent {

    void inject(CertainPlayersFragment fragment);

}

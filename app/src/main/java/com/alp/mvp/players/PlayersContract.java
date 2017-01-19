package com.alp.mvp.players;

import com.alp.library.base.ui.MVPLoadView;
import com.alp.library.presenter.IPresenter;

public class PlayersContract {

    public interface View extends MVPLoadView<String> {

    }

    public interface Presenter extends IPresenter {

        void getPlayers();
    }

}
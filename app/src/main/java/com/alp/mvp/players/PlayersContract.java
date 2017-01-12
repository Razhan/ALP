package com.alp.mvp.players;

import com.alp.library.base.ui.MVPView;
import com.alp.library.presenter.IPresenter;

public class PlayersContract {

    public interface View extends MVPView {

        void showPlayers();
    }

    public interface Presenter extends IPresenter {

        void getPlayers();
    }

}
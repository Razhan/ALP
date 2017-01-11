package com.alp.mvp.games;

import com.alp.library.base.ui.MVPView;
import com.alp.library.presenter.IPresenter;

public class GamesContract {

    interface View extends MVPView {

        void showGames();
    }

    interface Presenter extends IPresenter {

        void getGames();
    }

}

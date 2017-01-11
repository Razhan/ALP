package com.alp.mvp.games;

import com.alp.library.base.ui.MVPView;
import com.alp.library.presenter.IPresenter;

public class GamesContract {

    public interface View extends MVPView {

        void showGames();
    }

    public interface Presenter extends IPresenter {

        void getGames();
    }

}

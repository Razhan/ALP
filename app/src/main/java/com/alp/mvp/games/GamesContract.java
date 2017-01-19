package com.alp.mvp.games;

import com.alp.library.base.ui.MVPLoadView;
import com.alp.library.presenter.IPresenter;

public class GamesContract {

    public interface View extends MVPLoadView<String> {

    }

    public interface Presenter extends IPresenter {

        void getGames();
    }

}

package com.alp.mvp.league;

import com.alp.library.base.ui.MVPView;
import com.alp.library.presenter.IPresenter;

public class LeagueContract {

    public interface View extends MVPView {

        void showLeague();
    }

    public interface Presenter extends IPresenter {

        void getLeague();
    }
}

package com.alp.mvp.teams;

import com.alp.library.base.ui.MVPView;
import com.alp.library.presenter.IPresenter;

public class TeamsContract {

    public interface View extends MVPView {

        void showTeams();
    }

    public interface Presenter extends IPresenter {

        void getTeams();
    }

}
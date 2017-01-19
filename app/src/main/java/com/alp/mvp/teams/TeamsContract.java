package com.alp.mvp.teams;

import com.alp.library.base.ui.MVPLoadView;
import com.alp.library.presenter.IPresenter;

public class TeamsContract {

    public interface View extends MVPLoadView<String> {

    }

    public interface Presenter extends IPresenter {

        void getTeams();
    }

}
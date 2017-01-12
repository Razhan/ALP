package com.alp.mvp.teams.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.alp.library.base.ui.BaseMVPFragment;
import com.alp.mvp.ALPApplication;
import com.alp.mvp.R;
import com.alp.mvp.di.components.DaggerTeamsComponent;
import com.alp.mvp.di.modules.ActivityModule;
import com.alp.mvp.teams.TeamsContract;
import com.alp.mvp.teams.TeamsPresenter;

import butterknife.BindView;

public class CertainTeamsFragment extends BaseMVPFragment<TeamsPresenter> implements TeamsContract.View {

    private static final String KEY_BUNDLE = "count";

    @BindView(R.id.textview)
    TextView text;

    public static Fragment newInstance(int i) {
        Fragment fragment = new CertainTeamsFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_BUNDLE, i);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getPresenter().getTeams();
    }

    @Override
    public int getContentViewId() {
        return R.layout.fragment_page;
    }

    @Override
    protected void initializeInjector() {
        DaggerTeamsComponent.builder()
                .applicationComponent(((ALPApplication) activity.getApplication()).getApplicationComponent())
                .activityModule(new ActivityModule(activity))
                .build()
                .inject(this);
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        text.setText(String.valueOf(getArguments().getInt(KEY_BUNDLE)));
    }

    @Override
    public void showTeams() {
        Log.d(TAG, "showTeams");
    }

}

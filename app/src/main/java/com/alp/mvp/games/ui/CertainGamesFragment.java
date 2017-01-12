package com.alp.mvp.games.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.alp.library.base.ui.BaseMVPFragment;
import com.alp.mvp.ALPApplication;
import com.alp.mvp.R;
import com.alp.mvp.di.components.DaggerGamesComponent;
import com.alp.mvp.di.modules.ActivityModule;
import com.alp.mvp.games.GamesContract;
import com.alp.mvp.games.GamesPresenter;

import butterknife.BindView;

public class CertainGamesFragment extends BaseMVPFragment<GamesPresenter> implements GamesContract.View {
    private static final String KEY_BUNDLE = "count";

    @BindView(R.id.textview)
    TextView text;

    public static Fragment newInstance(int i) {
        Fragment fragment = new CertainGamesFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_BUNDLE, i);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getPresenter().getGames();
    }

    @Override
    public int getContentViewId() {
        return R.layout.fragment_page;
    }

    @Override
    protected void initializeInjector() {
        DaggerGamesComponent.builder()
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
    public void showGames() {
        Log.d(TAG, "showGames");
    }

}

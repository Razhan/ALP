package com.alp.mvp.players.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.alp.library.base.ui.BaseMVPFragment;
import com.alp.mvp.ALPApplication;
import com.alp.mvp.R;
import com.alp.mvp.di.components.DaggerPlayersComponent;
import com.alp.mvp.di.modules.ActivityModule;
import com.alp.mvp.players.PlayersContract;
import com.alp.mvp.players.PlayersPresenter;

import butterknife.BindView;

public class CertainPlayersFragment extends BaseMVPFragment<PlayersPresenter> implements PlayersContract.View {

    private static final String KEY_BUNDLE = "count";

    @BindView(R.id.textview)
    TextView text;

    public static Fragment newInstance(int i) {
        Fragment fragment = new CertainPlayersFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_BUNDLE, i);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getPresenter().getPlayers();
    }

    @Override
    public int getContentViewId() {
        return R.layout.fragment_page;
    }

    @Override
    protected void initializeInjector() {
        DaggerPlayersComponent.builder()
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
    public void showPlayers() {
        Log.d(TAG, "showPlayers");
    }

}

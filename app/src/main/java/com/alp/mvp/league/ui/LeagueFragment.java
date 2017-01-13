package com.alp.mvp.league.ui;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.alp.library.base.ui.BaseMVPFragment;
import com.alp.mvp.ALPApplication;
import com.alp.mvp.R;
import com.alp.mvp.di.components.DaggerLeagueComponent;
import com.alp.mvp.di.modules.ActivityModule;
import com.alp.mvp.league.LeagueContract;
import com.alp.mvp.league.LeaguePresenter;
import com.alp.mvp.setting.SettingActivity;
import com.alp.mvp.widgete.ArrowedTextView;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class LeagueFragment extends BaseMVPFragment<LeaguePresenter> implements LeagueContract.View {

    @BindView(R.id.league_bg)
    ImageView background;
    @BindView(R.id.league_avatar)
    CircleImageView avatar;
    @BindView(R.id.league_posts)
    ArrowedTextView posts;
    @BindView(R.id.league_venues)
    ArrowedTextView venues;
    @BindView(R.id.league_rules)
    ArrowedTextView rules;
    @BindView(R.id.league_fees)
    ArrowedTextView fees;
    @BindView(R.id.league_contact)
    ArrowedTextView contact;

    public static Fragment newInstance() {
        return new LeagueFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getPresenter().getLeague();
    }

    @Override
    public int getContentViewId() {
        return R.layout.fragment_league_detail;
    }

    @Override
    protected void initializeInjector() {
        DaggerLeagueComponent.builder()
                .applicationComponent(((ALPApplication) activity.getApplication()).getApplicationComponent())
                .activityModule(new ActivityModule(activity))
                .build()
                .inject(this);
    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }

    @Override
    public void showLeague() {

    }

    @OnClick(R.id.league_setting)
    public void onClick() {
        startActivity(new Intent(activity, SettingActivity.class));
    }
}

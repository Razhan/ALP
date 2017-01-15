package com.alp.mvp.main.ui;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.View;

import com.alp.library.base.helper.EnhancedFragmentManager;
import com.alp.library.base.ui.BaseActivity;
import com.alp.library.widget.navigationbar.NavigationBar;
import com.alp.library.widget.navigationbar.NavigationBarFragmentAdapter;
import com.alp.library.widget.navigationbar.NavigationItemClickListener;
import com.alp.mvp.R;
import com.alp.mvp.games.ui.GamesFragment;
import com.alp.mvp.league.ui.LeagueFragment;
import com.alp.mvp.players.ui.PlayersFragment;
import com.alp.mvp.teams.ui.TeamsFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.google.common.base.Preconditions.checkNotNull;

public class MainActivity extends BaseActivity implements NavigationItemClickListener {

    private static final int POS_GAMES = 0;
    private static final int POS_PLAYERS = 1;
    private static final int POS_TEAMS = 2;
    private static final int POS_LEAGUE = 3;

    @BindView(R.id.navigation_bar)
    NavigationBar navigationBar;

    private EnhancedFragmentManager fragmentManager;

    @Override
    public int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected boolean isShowBackIcon() {
        return false;
    }

    @Override
    protected String setToolBarText() {
        return "League TSC- Bandits";
    }

    @Override
    public void initData() {
        List<Fragment> fragments = new ArrayList<>();
        List<Integer> drawables = new ArrayList<>();
        List<String> tags = new ArrayList<>();
        FragmentManager manager = getFragmentManager();

        fragments.add(GamesFragment.newInstance());
        drawables.add(R.drawable.ic_people);
        tags.add("Games");

        fragments.add(PlayersFragment.newInstance());
        drawables.add(R.drawable.ic_people);
        tags.add("Players");

        fragments.add(TeamsFragment.newInstance());
        drawables.add(R.drawable.ic_people);
        tags.add("Teams");

        fragments.add(LeagueFragment.newInstance());
        drawables.add(R.drawable.ic_people);
        tags.add("Info");

        NavigationBarFragmentAdapter adapter = new NavigationBarFragmentAdapter(fragments, drawables, tags);

        fragmentManager = new EnhancedFragmentManager(manager, adapter, R.id.home_fragment);
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);

        navigationBar.setFragmentManager(fragmentManager);
        navigationBar.setListener(this);
    }

    @Override
    public void onBottomItemClick(int index) {
        checkNotNull(toolbar);
        checkNotNull(title);

        if (index == POS_LEAGUE) {
            toolbar.setVisibility(View.GONE);
        } else {
            toolbar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onBackPressed() {
        setDoubleClickExit(true);
        super.onBackPressed();
    }

}
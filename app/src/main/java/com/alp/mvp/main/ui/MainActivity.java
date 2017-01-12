package com.alp.mvp.main.ui;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;

import com.alp.library.base.helper.EnhancedFragmentManager;
import com.alp.library.base.ui.BaseActivity;
import com.alp.library.widget.navigationbar.NavigationBar;
import com.alp.library.widget.navigationbar.NavigationBarFragmentAdapter;
import com.alp.mvp.R;
import com.alp.mvp.games.ui.GamesFragment;
import com.alp.mvp.players.ui.PlayersFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

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
        return "ALP";
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

        fragments.add(TestFragment.newInstance("333333"));
        drawables.add(R.drawable.ic_people);
        tags.add("Teams");

        fragments.add(TestFragment.newInstance("444444"));
        drawables.add(R.drawable.ic_people);
        tags.add("Info");

        NavigationBarFragmentAdapter adapter = new NavigationBarFragmentAdapter(fragments, drawables, tags);

        fragmentManager = new EnhancedFragmentManager(manager, adapter, R.id.home_fragment);
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);

        navigationBar.setFragmentManager(fragmentManager);
    }
}

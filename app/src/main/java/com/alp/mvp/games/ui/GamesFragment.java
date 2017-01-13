package com.alp.mvp.games.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.util.Log;

import com.alp.mvp.base.BaseTabFragment;

public class GamesFragment extends BaseTabFragment {

    public static Fragment newInstance() {
        return new GamesFragment();
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);

        pagerAdapter.addFragment(CertainGamesFragment.newInstance(), "My Team");
        pagerAdapter.addFragment(CertainGamesFragment.newInstance(), "League");

        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

}

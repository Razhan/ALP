package com.alp.mvp.games.ui;

import android.app.Fragment;
import android.os.Bundle;

import com.alp.mvp.base.BaseTabFragment;

public class GamesFragment extends BaseTabFragment {

    public static Fragment newInstance() {
        return new GamesFragment();
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);

        pagerAdapter.addFragment(CertainGamesFragment.newInstance(3), "My Team");
        pagerAdapter.addFragment(CertainGamesFragment.newInstance(4), "League");

        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

}

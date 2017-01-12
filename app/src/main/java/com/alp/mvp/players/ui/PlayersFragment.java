package com.alp.mvp.players.ui;

import android.app.Fragment;
import android.os.Bundle;

import com.alp.mvp.base.BaseTabFragment;

public class PlayersFragment extends BaseTabFragment {

    public static Fragment newInstance() {
        return new PlayersFragment();
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);

        pagerAdapter.addFragment(CertainPlayersFragment.newInstance(1), "My Team");
        pagerAdapter.addFragment(CertainPlayersFragment.newInstance(2), "League");

        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

}

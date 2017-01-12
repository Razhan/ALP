package com.alp.mvp.teams.ui;

import android.app.Fragment;
import android.os.Bundle;

import com.alp.mvp.base.BaseTabFragment;

public class TeamsFragment extends BaseTabFragment {

    public static Fragment newInstance() {
        return new TeamsFragment();
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);

        pagerAdapter.addFragment(CertainTeamsFragment.newInstance(1), "Division A");
        pagerAdapter.addFragment(CertainTeamsFragment.newInstance(2), "Division B");
        pagerAdapter.addFragment(CertainTeamsFragment.newInstance(3), "Division C");
        pagerAdapter.addFragment(CertainTeamsFragment.newInstance(4), "Division D");

        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

}

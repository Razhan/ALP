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

        pagerAdapter.addFragment(CertainTeamsFragment.newInstance(), "Division A");
        pagerAdapter.addFragment(CertainTeamsFragment.newInstance(), "Division B");
        pagerAdapter.addFragment(CertainTeamsFragment.newInstance(), "Division C");
        pagerAdapter.addFragment(CertainTeamsFragment.newInstance(), "Division D");

        viewPager.setAdapter(pagerAdapter);
        viewPager.setOffscreenPageLimit(pagerAdapter.getCount() - 1);
        tabLayout.setupWithViewPager(viewPager);
    }

}

package com.alp.mvp.games.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.alp.library.base.ui.BaseFragment;
import com.alp.mvp.R;
import com.alp.mvp.adapter.FragmentAdapter;

import butterknife.BindView;

public class GamesFragment extends BaseFragment {

    @BindView(R.id.tablayout)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewPager;

    public static Fragment newInstance() {
        return new GamesFragment();
    }

    @Override
    public int getContentViewId() {
        return R.layout.fragment_games;
    }


    @Override
    public void initView(Bundle savedInstanceState) {
        FragmentAdapter pagerAdapter = new FragmentAdapter(getChildFragmentManager());

        pagerAdapter.addFragment(MyGamesFragment.newInstance(), "My Team");
        pagerAdapter.addFragment(AllGamesFragment.newInstance(), "League");

        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

}

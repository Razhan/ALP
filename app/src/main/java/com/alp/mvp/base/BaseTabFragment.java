package com.alp.mvp.base;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.alp.library.base.ui.BaseFragment;
import com.alp.mvp.R;
import com.alp.mvp.adapter.FragmentAdapter;

import butterknife.BindView;

public abstract class BaseTabFragment extends BaseFragment {

    @BindView(R.id.tablayout)
    public TabLayout tabLayout;
    @BindView(R.id.viewpager)
    public ViewPager viewPager;
    protected FragmentAdapter pagerAdapter;

    @Override
    public int getContentViewId() {
        return R.layout.fragment_tablayout;
    }

    @Override
    @CallSuper
    public void initView(Bundle savedInstanceState) {
        pagerAdapter = new FragmentAdapter(getChildFragmentManager());
    }

}

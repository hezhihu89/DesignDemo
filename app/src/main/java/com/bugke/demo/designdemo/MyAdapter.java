package com.bugke.demo.designdemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Hezhihu89 on 2016/10/20 0020.
 */

public class MyAdapter extends FragmentPagerAdapter {

    private PageAdapter mPageAdapter;

    public MyAdapter(FragmentManager manager,PageAdapter pageAdapter) {
        super(manager);
        this.mPageAdapter = pageAdapter;
    }

    @Override
    public int getCount() {
        return mPageAdapter.getCount();
    }

    @Override
    public Fragment getItem(int position) {
        return mPageAdapter.getItem(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mPageAdapter.getPageTitle(position);
    }
}

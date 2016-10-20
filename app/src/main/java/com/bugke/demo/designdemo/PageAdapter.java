package com.bugke.demo.designdemo;

import android.support.v4.app.Fragment;

/**
 * Created by Hezhihu89 on 2016/10/20 0020.
 */

public interface PageAdapter {
    Fragment getItem(int position);
    CharSequence getPageTitle(int position);
    int getCount();
}

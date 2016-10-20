package com.bugke.demo.designdemo.fragment;

import android.view.ViewGroup;

/**
 * Created by Hezhihu89 on 2016/10/20 0020.
 */

public interface RootLayout {
    void showLoading();
    void showSuccess();
    void showError();
    ViewGroup getParents();
}

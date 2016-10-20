package com.bugke.demo.designdemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Hezhihu89 on 2016/10/20 0020.
 */

public abstract class BaseFragment extends Fragment implements RootLayout{

    private RootLayout mRootView;

    public static class Pamans{
      public static final  String TILE = "title";
    }

    @Nullable
    public abstract RootLayout getCreateView();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRootView = getCreateView();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(mRootView != null){
           // mRootView.showLoading();
            mRootView.showSuccess();
           ViewGroup parent =  mRootView.getParents();
            if(null != parent){
                  parent.removeView((View) mRootView);
            }
        }
        return (View) mRootView;

    }





    @Override
    public void showLoading() {
       mRootView.showLoading();
    }

    @Override
    public void showSuccess() {
        mRootView.showSuccess();
    }

    @Override
    public void showError() {
       mRootView.showError();
    }

    @Override
    public ViewGroup getParents() {
        return mRootView.getParents();
    }
}

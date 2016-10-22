package com.bugke.demo.designdemo.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.ViewGroup;

import com.bugke.demo.designdemo.View.EmptyLayout;
import com.bugke.demo.designdemo.bean.AndroidBean;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Hezhihu89 on 2016/10/20 0020.
 */

public class FragmentPage extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener{

    private OkHttpClient mOkHttpClient;
    private BaseAdapter mAdapter;

    public static BaseFragment newInstance(String... egs) {
        Bundle bundle = new Bundle();
        bundle.putString(Pamans.TITLE,egs[0]);
        bundle.putString(Pamans.URL,egs[1]);
        FragmentPage fragmentPage = new FragmentPage();
        fragmentPage.setArguments(bundle);
        return fragmentPage;
    }

    @Nullable
    @Override
    public RootLayout getCreateView() {

        EmptyLayout emptyLayout = new EmptyLayout(getContext());

        SwipeRefreshLayout swipeRefreshLayout = new SwipeRefreshLayout(getContext());
        swipeRefreshLayout.setColorSchemeColors(Color.RED, Color.BLUE, Color.GREEN);
        RecyclerView recyclerView = new RecyclerView(getContext());
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mAdapter = new BaseAdapter(null);
        mAdapter.isFirstOnly(false);
        mAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        recyclerView.setAdapter(mAdapter);
        swipeRefreshLayout.addView(recyclerView,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        emptyLayout.bindView(swipeRefreshLayout);
        return emptyLayout;
    }

    @Override
    protected void getData() {
        refrash();
    }


    private void refrash() {
        showLoading();
        mOkHttpClient =new OkHttpClient();
        Request.Builder requestBuilder = new Request.Builder().url(URL);
        //可以省略，默认是GET请求
        requestBuilder.method("GET",null);
        Request request = requestBuilder.build();
        Call mcall= mOkHttpClient.newCall(request);
        mcall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (null != response.cacheResponse()) {
                    String str = response.cacheResponse().toString();
                } else {
                    String str = response.body().string();
                    Log.i("wangshu", "network---" + str);
                    final AndroidBean androidBean = new AndroidBean(str);
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mAdapter.setNewData(androidBean.mList);
                            showSuccess();
                        }
                    });
                }

            }
        });
    }



    @Override
    public void onRefresh() {

    }

}

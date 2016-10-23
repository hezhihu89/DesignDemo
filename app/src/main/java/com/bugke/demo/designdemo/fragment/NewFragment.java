package com.bugke.demo.designdemo.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.bugke.demo.designdemo.View.EmptyLayout;
import com.bugke.demo.designdemo.adapter.NewsAdapter;
import com.bugke.demo.designdemo.bean.NewBean;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Hezhihu89 on 2016/10/24 0024.
 */

public class NewFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener,BaseQuickAdapter.RequestLoadMoreListener{


    private NewsAdapter mAdapter;

    private OkHttpClient mOkHttpClient;

    private int currentItem = 10;
    private NewBean newBean;
    private SwipeRefreshLayout swipeRefreshLayout;

    public static BaseFragment newInstance(String... egs) {
        Bundle bundle = new Bundle();
        bundle.putString(Pamans.TITLE,egs[0]);
        bundle.putString(Pamans.URL,egs[1]);
        NewFragment fragmentPage = new NewFragment();
        fragmentPage.setArguments(bundle);
        return fragmentPage;
    }

    @Nullable
    @Override
    public RootLayout getCreateView() {
        EmptyLayout emptyLayout = new EmptyLayout(getContext());

        swipeRefreshLayout = new SwipeRefreshLayout(getContext());
        swipeRefreshLayout.setColorSchemeColors(Color.RED, Color.BLUE, Color.GREEN);
        RecyclerView recyclerView = new RecyclerView(getContext());
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new NewsAdapter(null);
        mAdapter.isFirstOnly(false);
        mAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        mAdapter.setOnLoadMoreListener(this);
        recyclerView.setAdapter(mAdapter);
        swipeRefreshLayout.addView(recyclerView,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        emptyLayout.bindView(swipeRefreshLayout);
        return emptyLayout;
    }

    public void getData(int data){
        currentItem = data;
        getData();
    }

    @Override
    protected void getData() {
        mOkHttpClient =new OkHttpClient();
        Request.Builder requestBuilder = new Request.Builder().url(URL+0+"-"+currentItem+".html");
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
                    if(null == newBean){
                        newBean = new NewBean(str);
                    }else{
                        newBean.parsDatas(str);
                    }
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mAdapter.setNewData(newBean.mList);
                            showSuccess();
                            swipeRefreshLayout.setRefreshing(false);
                            mAdapter.hiedLoadingMore();
                        }
                    });
                }

            }
        });
    }

    @Override
    public void onRefresh() {
        getData(currentItem);
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void onLoadMoreRequested() {
        getData(currentItem+10);
    }
}

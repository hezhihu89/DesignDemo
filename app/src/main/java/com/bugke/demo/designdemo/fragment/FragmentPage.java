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
 * "http://p.codekk.com/api/op/page/0?type=mix"
 * Created by Hezhihu89 on 2016/10/20 0020.
 */

public class FragmentPage extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener,BaseQuickAdapter.RequestLoadMoreListener{

    private OkHttpClient mOkHttpClient;
    private BaseAdapter mAdapter;
    private int mCurrentPage = 0;
    private SwipeRefreshLayout swipeRefreshLayout;
    private AndroidBean androidBean;

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
        swipeRefreshLayout = new SwipeRefreshLayout(getContext());
        swipeRefreshLayout.setColorSchemeColors(Color.RED, Color.BLUE, Color.GREEN);
        RecyclerView recyclerView = new RecyclerView(getContext());
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mAdapter = new BaseAdapter(null);
        mAdapter.isFirstOnly(false);
        mAdapter.setOnLoadMoreListener(this);
        mAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        recyclerView.setAdapter(mAdapter);
        swipeRefreshLayout.addView(recyclerView,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        emptyLayout.bindView(swipeRefreshLayout);
        return emptyLayout;
    }

    public void getData(int mCurrentPage){
        this.mCurrentPage = mCurrentPage;
        getData();
    }


    @Override
    protected void getData() {

        mOkHttpClient =new OkHttpClient();

        Request.Builder requestBuilder = new Request.Builder().url(URL+mCurrentPage+"?type=mix");
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
                    if(null == androidBean){
                        androidBean = new AndroidBean(str);
                    }else{
                        androidBean.parsDatas(str);
                    }

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mAdapter.setNewData(androidBean.mList);
                            swipeRefreshLayout.setRefreshing(false);
                            mAdapter.hiedLoadingMore();
                            showSuccess();
                        }
                    });
                }

            }
        });
    }





    @Override
    public void onRefresh() {
       getData(0);
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void onLoadMoreRequested() {
       getData(mCurrentPage+=1);
    }
}

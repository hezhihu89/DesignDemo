package com.bugke.demo.designdemo.fragment;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.bugke.demo.designdemo.View.EmptyLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;

/**
 * Created by Hezhihu89 on 2016/10/20 0020.
 */

public class FragmentPage extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener{

    private ArrayList<ItemInfo> mDatas;

    public static BaseFragment newInstance(String... egs) {
        FragmentPage fragmentPage = new FragmentPage();
        return fragmentPage;
    }

    @Nullable
    @Override
    public RootLayout getCreateView() {
        initData();
        EmptyLayout emptyLayout = new EmptyLayout(getContext());
        SwipeRefreshLayout swipeRefreshLayout = new SwipeRefreshLayout(getContext());
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeColors(Color.RED, Color.BLUE, Color.GREEN);
        RecyclerView recyclerView = new RecyclerView(getContext());
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        BaseAdapter b = new BaseAdapter(mDatas);
        b.isFirstOnly(false);
        b.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        recyclerView.setAdapter(b);
        swipeRefreshLayout.addView(recyclerView,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        emptyLayout.bindView(swipeRefreshLayout);
        return emptyLayout;
    }

    public void initData(){
        mDatas = new ArrayList<>();
        String TITLE = "测试数据";
        for(int i=0 ;i<100;i++){
            String title = TITLE + i;
            ItemInfo itemInfo = new ItemInfo(title,TITLE);
            mDatas.add(itemInfo);
        }
    }

    class ItemInfo{
        String TITLE;
        String iconUrl;

        public ItemInfo(String TITLE, String iconUrl) {
            this.TITLE = TITLE;
            this.iconUrl = iconUrl;
        }

        public String getTITLE() {
            return TITLE;
        }

        public String getIconUrl() {
            return iconUrl;
        }
    }

    @Override
    public void onRefresh() {

    }
}

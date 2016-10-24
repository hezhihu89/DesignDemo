package com.bugke.demo.designdemo;

import android.app.Application;

import com.baidu.location.LocationClient;
import com.bugke.demo.designdemo.baidu.BaiDuLocationListener;

/**
 * Created by Hezhihu89 on 2016/10/22 0022.
 */

public class APP extends Application {


    public LocationClient mLocationClient = null;
    public BaiDuLocationListener mBaiduLocationListener = null;

    @Override
    public void onCreate() {
        super.onCreate();
        initBaiduLocation();
    }

    private void initBaiduLocation() {
        mLocationClient = new LocationClient(getApplicationContext());     //声明LocationClient类
        mBaiduLocationListener =  BaiDuLocationListener.getIns(this.getApplicationContext());
        mBaiduLocationListener.init(mLocationClient);
        mLocationClient.registerLocationListener(mBaiduLocationListener);    //注册监听函数
    }

}

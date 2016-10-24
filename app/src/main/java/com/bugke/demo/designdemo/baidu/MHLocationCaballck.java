package com.bugke.demo.designdemo.baidu;

import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;

/**
 * Created by Hezhihu89 on 2016/10/24 0024.
 */
public interface MHLocationCaballck {
    void locationCallBack(BDLocation location, LocationClient client);
}

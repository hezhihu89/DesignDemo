package com.bugke.demo.designdemo.AndroidPage;

import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.request.BaseRequest;

import okhttp3.Response;

/**
 * Created by Hezhihu89 on 2016/10/22 0022.
 */

public abstract class AndroidCallBack<T> extends AbsCallback<T>{


    @Override
    public void onBefore(BaseRequest request) {
        //缓存演示代码所有请求需要添加 apikey
    }

    @Override
    public T convertSuccess(Response response) throws Exception {




        return null;
    }
}

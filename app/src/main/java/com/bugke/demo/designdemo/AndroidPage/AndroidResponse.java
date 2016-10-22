package com.bugke.demo.designdemo.AndroidPage;

import com.lzy.okgo.callback.AbsCallback;

import java.io.Serializable;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Hezhihu89 on 2016/10/22 0022.
 */

public class AndroidResponse<T> extends AbsCallback<Object> implements Serializable {
    @Override
    public void onSuccess(Object o, Call call, Response response) {

    }

    @Override
    public Object convertSuccess(Response response) throws Exception {
        return null;
    }
}

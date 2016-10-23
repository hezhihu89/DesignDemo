package com.bugke.demo.designdemo.bean;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hezhihu89 on 2016/10/24 0024.
 */

public class NewBean {

    private static final String TAG = NewBean.class.getSimpleName();

    public  int code;

    public  String message;


    public List<NewsItem> mList = new ArrayList<>();

    public NewBean(String body) {
        parsData(body);
    }

    public void parsDatas(String body){
        parsData(body);
    }

    private void parsData(String str) {

        try {

            str = str.replace("artiList(","");

            str = str.substring(0,str.lastIndexOf(")"));

            JSONObject jsonObject1 = new JSONObject(str);

            JSONArray jsonArray = jsonObject1.getJSONArray("BA10TA81wangning");

            Log.d(TAG, "parsData: ====="+ jsonArray.toString());

             mList =new Gson().fromJson(jsonArray.toString(), new TypeToken<ArrayList<NewsItem>>() {}.getType());

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}

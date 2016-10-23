package com.bugke.demo.designdemo.bean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Hezhihu89 on 2016/10/22 0022.
 */

public class AndroidBean{

    public  int code;

    public  String message;


    public  List<ProjectDate> mList = new ArrayList<>();

    public AndroidBean(String body) {
        parsData(body);
    }

    private void parsData(String str) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            JSONObject data = jsonObject.getJSONObject("data");

            message = jsonObject.getString("message");

            data = new JSONObject(data.toString());

            JSONObject projectDateMap = data.getJSONObject("projectDateMap");

            projectDateMap.length();

            Iterator<String> keys = projectDateMap.keys();

            while (keys.hasNext()){
                String strs = keys.next();
                parsData(projectDateMap,strs);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void parsData(JSONObject projectDateMap, String strs) throws JSONException {

        ProjectDate projectDate = new ProjectDate(true,strs);
        mList.add(projectDate);

        JSONArray jsonArray = projectDateMap.getJSONArray(strs);
        for(int i = 0;i<jsonArray.length();i++){

            JSONObject jsonObject  = (JSONObject) jsonArray.get(i);
            //  Log.d(TAG, "parsData2: " + jsonObject.toString());
            String id =   jsonObject.getString("_id");
            String projectName =   jsonObject.getString("projectName");
            String createTime =   jsonObject.getString("createTime");
            String projectUrl =   jsonObject.getString("projectUrl");
            projectUrl = projectUrl.replaceAll("\\\\","");
            String desc =   jsonObject.getString("desc");
            desc = desc.replaceAll("\\\\","");
            ItemData itemData = new ItemData(id,projectName,projectUrl,createTime,desc);
            ProjectDate projectDate1 = new ProjectDate(itemData);
            mList.add(projectDate1);
            // Log.d(TAG, "parsData21: " + id);
        }

    }

}

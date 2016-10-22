package com.bugke.demo.designdemo.bean;

import android.util.Log;


public class ItemData {
private static final String TAG = ItemData.class.getSimpleName();
        String id;
        String projectName;
        String createTime;
        String projectUrl;
        String desc;

        public ItemData(String id, String projectName, String projectUrl, String createTime, String desc) {
                Log.d(TAG, "ItemData: =============== url :" +projectUrl );
                this.id = id;
                this.projectName = projectName;
                this.projectUrl = projectUrl;
                this.createTime = createTime;
                this.desc = desc;
        }

        public String getId() {
                return id;
        }

        public String getProjectName() {
                return projectName;
        }

        public String getCreateTime() {
                return createTime;
        }

        public String getProjectUrl() {
                return projectUrl;
        }

        public String getDesc() {
                return desc;
        }
}
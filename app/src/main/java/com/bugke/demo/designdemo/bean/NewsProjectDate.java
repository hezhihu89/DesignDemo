package com.bugke.demo.designdemo.bean;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * Created by Hezhihu89 on 2016/10/24 0024.
 */
public class NewsProjectDate extends SectionEntity<NewsItem> {

    public NewsProjectDate(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public NewsProjectDate(NewsItem newsItem) {
        super(newsItem);
    }
}

package com.bugke.demo.designdemo.adapter;

import android.text.TextUtils;
import android.view.View;

import com.bugke.demo.designdemo.R;
import com.bugke.demo.designdemo.WebActivity;
import com.bugke.demo.designdemo.bean.NewsItem;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by Hezhihu89 on 2016/10/24 0024.
 */

public class NewsAdapter extends BaseQuickAdapter<NewsItem> {

    public NewsAdapter(List<NewsItem> data) {
        super(R.layout.item_home,data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, final NewsItem newsItem) {
          baseViewHolder.setText(R.id.item_title,newsItem.getTitle());
          baseViewHolder.setText(R.id.item_text,newsItem.getDigest());
        baseViewHolder.setOnClickListener(baseViewHolder.getConvertView().getId(), new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String Url ="";
           if(TextUtils.isEmpty(newsItem.getSkipType())){
               Url = "http://3g.163.com/touch/article.html?docid="+newsItem.getDocid();
           }else{
               Url = newsItem.getSkipURL();
           }
                WebActivity.runActivity(view.getContext(),newsItem.getTitle(),Url);
            }
        });
    }
}

package com.bugke.demo.designdemo.fragment;

import android.view.View;

import com.bugke.demo.designdemo.R;
import com.bugke.demo.designdemo.WebActivity;
import com.bugke.demo.designdemo.bean.ProjectDate;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by Hezhihu89 on 2016/10/21 0021.
 */

public class BaseAdapter extends BaseSectionQuickAdapter<ProjectDate> {

    public BaseAdapter(List data) {
        super(R.layout.item_home,R.layout.head,data);
    }


    @Override
    protected void convertHead(BaseViewHolder baseViewHolder, ProjectDate projectDate) {
         baseViewHolder.setText(R.id.head_date,projectDate.header);

    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, final ProjectDate projectDate) {
        baseViewHolder.setText(R.id.item_title,projectDate.t.getProjectName());
        baseViewHolder.setText(R.id.item_text,projectDate.t.getDesc());
        baseViewHolder.setOnClickListener(baseViewHolder.getConvertView().getId(), new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                WebActivity.runActivity(view.getContext(),projectDate.t.getProjectName(),projectDate.t.getProjectUrl());
            }
        });
    }
}

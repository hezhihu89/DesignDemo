package com.bugke.demo.designdemo.fragment;

import com.bugke.demo.designdemo.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by Hezhihu89 on 2016/10/21 0021.
 */

public class BaseAdapter extends BaseQuickAdapter<FragmentPage.ItemInfo> {

    public BaseAdapter(List data) {
        super(R.layout.item_home,data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, FragmentPage.ItemInfo itemInfo) {
        baseViewHolder.setText(R.id.item_text,itemInfo.getTITLE());
    }
}

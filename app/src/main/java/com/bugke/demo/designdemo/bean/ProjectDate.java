package com.bugke.demo.designdemo.bean;

import com.chad.library.adapter.base.entity.SectionEntity;

public class ProjectDate extends SectionEntity<ItemData>{

    public ProjectDate(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public ProjectDate(ItemData itemData) {
        super(itemData);
    }

}
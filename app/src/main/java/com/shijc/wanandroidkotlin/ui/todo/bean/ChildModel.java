package com.shijc.wanandroidkotlin.ui.todo.bean;

import com.shijc.wanandroidkotlin.R;
import com.shijc.wanandroidkotlin.widget.treeview.LayoutItemType;

import java.io.Serializable;

public class ChildModel implements Serializable, LayoutItemType {
    private String id;
    private String name;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_todo_child;
    }

}

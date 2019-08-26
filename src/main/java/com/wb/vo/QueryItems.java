package com.wb.vo;

import com.wb.entity.Items;

import java.util.List;

public class QueryItems {
    private Items items;
    private List<Integer> ids;

    public Items getItems() {
        return items;
    }

    public void setItems(Items items) {
        this.items = items;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }


}

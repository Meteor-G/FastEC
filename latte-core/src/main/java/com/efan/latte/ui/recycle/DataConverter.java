package com.efan.latte.ui.recycle;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/12/19.
 * 数据转换约束
 */

public abstract class DataConverter {
    protected final ArrayList<MultipleItemEntity> ENTITYS = new ArrayList<>();
    private String mJsonData = null;

    public abstract ArrayList<MultipleItemEntity> convent();

    public DataConverter setJsonData(String json) {
        this.mJsonData = json;
        return this;
    }

    protected String getJsonData() {
        if (mJsonData == null || mJsonData.isEmpty()) {
            throw new NullPointerException("DATA IS UNLL ! ");
        }
        return mJsonData;
    }

}

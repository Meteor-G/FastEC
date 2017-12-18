package com.efan.ec.main;

import android.graphics.Color;

import com.efan.ec.main.index.IndexDelegate;
import com.efan.ec.main.sort.SortDelegate;
import com.efan.latte.delegates.bottom.BaseBottomDelegate;
import com.efan.latte.delegates.bottom.BottomItemDelegate;
import com.efan.latte.delegates.bottom.BottomTabBean;
import com.efan.latte.delegates.bottom.ItemBuilder;

import java.util.LinkedHashMap;

/**
 * Created by Administrator on 2017/12/18.
 */

public class EcBottomDelegate extends BaseBottomDelegate {
    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder) {
        final LinkedHashMap<BottomTabBean, BottomItemDelegate> items = new LinkedHashMap<>();
        items.put(new BottomTabBean("{fa-home}", "主页"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-sort}", "分类"), new SortDelegate());
        items.put(new BottomTabBean("{fa-compass}", "发现"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-shopping-cart}", "购物车"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-user}", "我的"), new IndexDelegate());
        return builder.addItems(items).build();
    }

    @Override
    public int setIndexDelegate() {
        return 0;
    }

    @Override
    public int setClickedColor() {
        return Color.parseColor("#ffff8800");
    }
}

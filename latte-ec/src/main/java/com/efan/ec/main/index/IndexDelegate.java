package com.efan.ec.main.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.efan.ec.R;
import com.efan.latte.delegates.bottom.BottomItemDelegate;

/**
 * Created by Administrator on 2017/12/18.
 */

public class IndexDelegate extends BottomItemDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
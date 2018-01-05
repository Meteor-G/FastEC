package com.efan.latte.ui.refresh;

import android.content.res.Resources;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.efan.latte.R;
import com.efan.latte.app.Latte;
import com.efan.latte.ui.recycle.DataConverter;
import com.efan.latte.ui.recycle.MultipleRecycleAdapter;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2017/12/19.
 */

public class RefreshHandler implements SwipeRefreshLayout.OnRefreshListener,
        BaseQuickAdapter.RequestLoadMoreListener {
    private final SwipeRefreshLayout REFRESH_LAYOUT;
    private final PagingBean BEAN;
    private final RecyclerView RECYCLEVIEW;
    private MultipleRecycleAdapter mAdapter = null;
    private final DataConverter COUVERTER;

    private RefreshHandler(SwipeRefreshLayout swipeRefreshLayout,
                           RecyclerView recyclerView,
                           DataConverter converter,
                           PagingBean bean) {
        this.REFRESH_LAYOUT = swipeRefreshLayout;
        this.RECYCLEVIEW = recyclerView;
        this.BEAN = bean;
        this.COUVERTER = converter;
        //监听事件
        REFRESH_LAYOUT.setOnRefreshListener(this);
    }

    public static RefreshHandler create(SwipeRefreshLayout swipeRefreshLayout,
                                        RecyclerView recyclerView,
                                        DataConverter converter) {
        return new RefreshHandler(swipeRefreshLayout, recyclerView, converter, new PagingBean());

    }

    private void refresh() {
        // 开始刷新，设置当前为刷新状态
        REFRESH_LAYOUT.setRefreshing(true);
        //延迟执行
        Latte.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //可以在这进行一些网络请求

                // 加载完数据设置为不刷新状态，将下拉进度收起来
                REFRESH_LAYOUT.setRefreshing(false);
            }
        }, 2000);
    }

    public void firstPage(String url) {
        BEAN.setDelayed(1000);
        String response;
        Resources resources = Latte.getApplication().getResources();
        InputStream is = null;
        try {
            is = resources.openRawResource(R.raw.index_data);
            byte buffer[] = new byte[is.available()];
            is.read(buffer);
            response = new String(buffer);
            initData(response);
        } catch (IOException e) {

        }
//        RestClient.builder()
//                .url(url)
//                .success(new ISuccess() {
//                    @Override
//                    public void onSuccess(String response) {
//                        Toast.makeText(Latte.getApplication(), "请求的数据为" + response, Toast.LENGTH_LONG).show();
//                        final JSONObject object = JSONObject.parseObject(response);
//                        BEAN.setTotal(object.getInteger("total"))
//                                .setPageSize(object.getInteger("page_size"));
//                        //设置Adapter
//                        mAdapter = MultipleRecycleAdapter.create(COUVERTER.setJsonData(response));
//                        mAdapter.setOnLoadMoreListener(RefreshHandler.this, RECYCLEVIEW);
//                        RECYCLEVIEW.setAdapter(mAdapter);
//                        BEAN.addIndex();
//                    }
//                })
//                .build()
//                .get();
    }

    private void initData(String response) {
        Toast.makeText(Latte.getApplication(), "请求的数据为" + response, Toast.LENGTH_LONG).show();
        final JSONObject object = JSONObject.parseObject(response);
        BEAN.setTotal(object.getInteger("total"))
                .setPageSize(object.getInteger("page_size"));
        //设置Adapter
//        mAdapter = MultipleRecycleAdapter.create(COUVERTER.setJsonData(response));
//        mAdapter.setOnLoadMoreListener(RefreshHandler.this, RECYCLEVIEW);
//        RECYCLEVIEW.setAdapter(mAdapter);
//        BEAN.addIndex();
    }

    //监听Refresh操作
    @Override
    public void onRefresh() {
        refresh();
    }

    @Override
    public void onLoadMoreRequested() {

    }
}

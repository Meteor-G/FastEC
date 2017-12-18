package com.efan.fastec.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.efan.latte.delegates.LatteDelegate;
import com.efan.latte.net.RestClient;
import com.efan.latte.net.callback.IError;
import com.efan.latte.net.callback.IFailure;
import com.efan.latte.net.callback.ISuccess;

/**
 * Created by Administrator on 2017/11/30.
 */

public class ExampleDelegate extends LatteDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
//        testRestClient();
    }

    public void testRestClient() {
        RestClient.builder()
                .url("https://127.0.0.1/index")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {

                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .build()
                .get();
    }


}

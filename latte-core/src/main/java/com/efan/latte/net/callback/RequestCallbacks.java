package com.efan.latte.net.callback;

import android.os.Handler;

import com.efan.latte.ui.loader.LatteLoader;
import com.efan.latte.ui.loader.LoaderStyle;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/12/2.
 */

public class RequestCallbacks implements Callback<String> {
    private final IError ERROR;
    private final IFailure FAILURE;
    private final IRequset REQUEST;
    private final ISuccess SUCCESS;
    private final LoaderStyle LOADERSTYLE;

    private static final Handler HANDLER = new Handler();

    public RequestCallbacks(IError error, IFailure failure, IRequset requset, ISuccess success, LoaderStyle loaderStyle) {
        this.ERROR = error;
        this.FAILURE = failure;
        this.REQUEST = requset;
        this.SUCCESS = success;
        this.LOADERSTYLE = loaderStyle;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (response.isSuccessful()) {
            if (call.isExecuted()) {
                if (SUCCESS != null) {
                    SUCCESS.onSuccess(response.body());
                }
            }
        } else {
            if (ERROR != null) {
                ERROR.onError(response.code(), response.message());
            }
        }
        stopLoading();
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if (FAILURE != null) {
            FAILURE.onFailure();
        }
        if (REQUEST != null) {
            REQUEST.onRequestEnd();
        }
        stopLoading();
    }

    private void stopLoading() {
        if (LOADERSTYLE != null) {
            HANDLER.postDelayed(new Runnable() {
                @Override
                public void run() {
                    LatteLoader.stopLoading();
                }
            }, 1000);
        }
    }

}

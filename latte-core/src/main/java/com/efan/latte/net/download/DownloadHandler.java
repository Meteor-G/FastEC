package com.efan.latte.net.download;

import android.os.AsyncTask;

import com.efan.latte.net.RestCreator;
import com.efan.latte.net.callback.IError;
import com.efan.latte.net.callback.IFailure;
import com.efan.latte.net.callback.IRequset;
import com.efan.latte.net.callback.ISuccess;

import java.util.WeakHashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/12/3.
 */

public class DownloadHandler {

    private final String URL;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final IError ERROR;
    //目录
    private final String DOWNLOAD_DIR;
    //下载的后缀
    private final String EXTENSION;
    //下载的文件名
    private final String NAME;
    private final IFailure FAILURE;
    private final IRequset REQUEST;
    private final ISuccess SUCCESS;

    public DownloadHandler(String url,
                           IError error,
                           String downDir,
                           String extension,
                           String name,
                           IFailure failure,
                           IRequset requset,
                           ISuccess success) {
        this.URL = url;
        this.ERROR = error;
        this.DOWNLOAD_DIR = downDir;
        this.EXTENSION = extension;
        this.NAME = name;
        this.FAILURE = failure;
        this.REQUEST = requset;
        this.SUCCESS = success;
    }

    public final void handledownload() {
        //开始下载
        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }
        RestCreator.getRestService().download(URL, PARAMS)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            final ResponseBody responseBody = response.body();
                            final SaveFileTask task = new SaveFileTask(REQUEST, SUCCESS);
                            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, DOWNLOAD_DIR, EXTENSION, response, NAME);
                            //这里一定要注意判断，否则文件下载不全
                            if (task.isCancelled()) {
                                if (REQUEST != null) {
                                    REQUEST.onRequestEnd();
                                }
                            }
                        } else {
                            if (ERROR != null) {
                                ERROR.onError(response.code(), response.message());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        if (FAILURE != null) {
                            FAILURE.onFailure();
                        }
                    }
                });
    }
}

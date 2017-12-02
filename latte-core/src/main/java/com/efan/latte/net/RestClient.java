package com.efan.latte.net;

import android.content.Context;

import com.efan.latte.net.callback.IError;
import com.efan.latte.net.callback.IFailure;
import com.efan.latte.net.callback.IRequset;
import com.efan.latte.net.callback.ISuccess;
import com.efan.latte.net.callback.RequestCallbacks;
import com.efan.latte.ui.LatteLoader;
import com.efan.latte.ui.LoaderStyle;

import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.ResponseBody;
import retrofit2.Callback;

/**
 * Created by Administrator on 2017/11/30.
 */
/*
 *建造者模式
*/
public class RestClient {

    private final String URL;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final IError ERROR;
    private final IFailure FAILURE;
    private final IRequset REQUEST;
    private final ISuccess SUCCESS;
    private final ResponseBody BOBY;
    private final LoaderStyle LOADER_STYLE;
    private final Context CONTEXT;

    public RestClient(String url,
                      Map<String, Object> params,
                      IError error,
                      IFailure failure,
                      IRequset requset,
                      ISuccess success,
                      ResponseBody body,
                      LoaderStyle loaderStyle,
                      Context context) {
        this.URL = url;
        PARAMS.putAll(params);
        this.ERROR = error;
        this.FAILURE = failure;
        this.REQUEST = requset;
        this.SUCCESS = success;
        this.BOBY = body;
        this.LOADER_STYLE = loaderStyle;
        this.CONTEXT = context;
    }

    public static RestClickBuilder builder() {
        return new RestClickBuilder();
    }

    private void request(HttpMethod method) {
        final RestService service = RestCreator.getRestService();
        retrofit2.Call<String> call = null;
        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }
        //显示Loader
        if (LOADER_STYLE != null) {
            LatteLoader.showLoading(CONTEXT, LOADER_STYLE);
        }
        switch (method) {
            case GET:
                call = service.get(URL, PARAMS);
                break;
            case POST:
                call = service.post(URL, PARAMS);
                break;
            case PUT:
                call = service.put(URL, PARAMS);
                break;
            case DELETE:
                call = service.delete(URL, PARAMS);
                break;
            default:
                break;
        }
        if (call != null) {
            call.enqueue(getRequestCallback());
        }
    }

    private Callback<String> getRequestCallback() {
        return new RequestCallbacks(
                ERROR,
                FAILURE,
                REQUEST,
                SUCCESS,
                LOADER_STYLE);
    }

    public final void get() {
        request(HttpMethod.GET);
    }

    public final void post() {
        request(HttpMethod.POST);
    }

    public final void put() {
        request(HttpMethod.PUT);
    }

    public final void delete() {
        request(HttpMethod.DELETE);
    }
}

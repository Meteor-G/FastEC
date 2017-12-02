package com.efan.latte.net;

import android.content.Context;

import com.efan.latte.net.callback.IError;
import com.efan.latte.net.callback.IFailure;
import com.efan.latte.net.callback.IRequset;
import com.efan.latte.net.callback.ISuccess;
import com.efan.latte.ui.LoaderStyle;

import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2017/12/2.
 */

public class RestClickBuilder {

    private String mUrl = null;
    private static final Map<String, Object> PARAMS = RestCreator.getParams();
    private IError mIError;
    private IFailure mIFailure;
    private IRequset mIRequset;
    private ISuccess mISuccess;
    private ResponseBody mBody;
    private Context mContext;
    private LoaderStyle mLoaderStyle;

    RestClickBuilder() {

    }

    public final RestClickBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public final RestClickBuilder params(WeakHashMap<String, Object> params) {
        PARAMS.putAll(params);
        return this;
    }

    public final RestClickBuilder params(String key, Object value) {
        PARAMS.put(key, value);
        return this;
    }

    public final RestClickBuilder raw(String raw) {
        this.mBody = ResponseBody.create(MediaType.parse("application/json;charset=utf-8"), raw);
        return this;
    }

    public final RestClickBuilder success(ISuccess iSuccess) {
        this.mISuccess = iSuccess;
        return this;
    }

    public final RestClickBuilder error(IError iError) {
        this.mIError = iError;
        return this;
    }

    public final RestClickBuilder failure(IFailure iFailure) {
        this.mIFailure = iFailure;
        return this;
    }

    public final RestClickBuilder onRequset(IRequset iRequset) {
        this.mIRequset = iRequset;
        return this;
    }

    public final RestClickBuilder loader(Context context) {
        this.mContext = context;
        this.mLoaderStyle = LoaderStyle.BallSpinFadeLoaderIndicator;
        return this;

    }


    public final RestClient build() {
        return new RestClient(mUrl, PARAMS, mIError, mIFailure, mIRequset, mISuccess, mBody, mLoaderStyle, mContext);
    }
}

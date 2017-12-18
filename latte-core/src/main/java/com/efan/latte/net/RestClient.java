package com.efan.latte.net;

import android.content.Context;

import com.efan.latte.net.callback.IError;
import com.efan.latte.net.callback.IFailure;
import com.efan.latte.net.callback.IRequset;
import com.efan.latte.net.callback.ISuccess;
import com.efan.latte.net.callback.RequestCallbacks;
import com.efan.latte.net.download.DownloadHandler;
import com.efan.latte.ui.loader.LatteLoader;
import com.efan.latte.ui.loader.LoaderStyle;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
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
    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;
    private final IFailure FAILURE;
    private final IRequset REQUEST;
    private final ISuccess SUCCESS;
    private final RequestBody BOBY;
    private final File FILE;
    private final LoaderStyle LOADER_STYLE;
    private final Context CONTEXT;


    public RestClient(String url,
                      Map<String, Object> params,
                      String downloadDir,
                      String extension,
                      String name,
                      IError error,
                      IFailure failure,
                      IRequset requset,
                      ISuccess success,
                      RequestBody body,
                      File file,
                      LoaderStyle loaderStyle,
                      Context context) {
        this.URL = url;
        PARAMS.putAll(params);
        this.DOWNLOAD_DIR = downloadDir;
        this.EXTENSION = extension;
        this.NAME = name;
        this.ERROR = error;
        this.FAILURE = failure;
        this.REQUEST = requset;
        this.SUCCESS = success;
        this.BOBY = body;
        this.LOADER_STYLE = loaderStyle;
        this.CONTEXT = context;
        this.FILE = file;
    }

    public static RestClientBuilder builder() {
        return new RestClientBuilder();
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
            case POST_RAM:
                call = service.postRaw(URL, BOBY);
                break;
            case PUT:
                call = service.put(URL, PARAMS);
                break;
            case PUT_RAM:
                call = service.putRaw(URL, BOBY);
                break;
            case DELETE:
                call = service.delete(URL, PARAMS);
                break;
            case UPLOAD:
                final RequestBody requestBody =
                        RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);
                final MultipartBody.Part boby =
                        MultipartBody.Part.createFormData("file", FILE.getName(), requestBody);
                call = RestCreator.getRestService().upload(URL, boby);
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
        if (BOBY == null) {
            request(HttpMethod.POST);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null");
            }
            request(HttpMethod.POST_RAM);
        }

    }

    public final void put() {
        if (BOBY == null) {
            request(HttpMethod.PUT);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null");
            }
            request(HttpMethod.PUT_RAM);
        }
    }

    public final void delete() {
        request(HttpMethod.DELETE);
    }

    //上传
    public final void upload() {
        request(HttpMethod.UPLOAD);
    }

    //下载
    public final void download() {
        new DownloadHandler(URL,
                ERROR,
                DOWNLOAD_DIR,
                EXTENSION,
                NAME,
                FAILURE,
                REQUEST,
                SUCCESS).
                handledownload();
    }
}

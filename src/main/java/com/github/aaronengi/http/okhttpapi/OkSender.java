package com.github.aaronengi.http.okhttpapi;

import com.github.aaronengi.http.async.IHttpSender;
import com.github.aaronengi.http.async.RequestData;
import com.github.aaronengi.http.async.ResponseData;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by wangchao on 2017/3/7.
 */

public class OkSender implements IHttpSender {
    private static final String TAG = "OkSender";
    private final OkHttpClient client;

    public OkSender(OkHttpClient client) {
        this.client = client;
    }

    public void send(final RequestData data) {
//        if (!NetHelper.isConnected()) {
//            if(data.listener != null){
//                data.listener.onError(0, "无网络");
//            }
//            return;
//        }
        Request.Builder builder = new Request.Builder();
        builder.url(data.url);
        Set<Map.Entry<String, String>> set = data.headers.entrySet();
        Iterator<Map.Entry<String, String>> iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            if (entry.getValue() == null) {
                System.out.println(String.format("entry:%s:%s", entry.getKey(), entry.getValue()));
//                LogHelper.w(TAG, String.format("entry:%s:%s", entry.getKey(), entry.getValue()));
            } else {
                builder.addHeader(entry.getKey(), entry.getValue());
            }

        }

        if ("POST".equalsIgnoreCase(data.method)) {
            RequestBody body = null;
            if (data.body != null) {
                body = create(data.body);
            } else if (data.params != null) {
                body = create(data.params);
            }
            builder.post(body);
        } else if ("PUT".equalsIgnoreCase(data.method)) {
            RequestBody body = null;
            if (data.body != null) {
                body = create(data.body);
            } else if (data.params != null) {
                body = create(data.params);
            }
            builder.put(body);
        } else if ("DELETE".equalsIgnoreCase(data.method)) {
            builder.delete();
        } else {
            //默认GET
        }

        Request request = builder.build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (data.listener != null) {
                    data.listener.onError(0, "网络有问题", null);
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
//                RequestLog.logHeader(call.request());
//                ResponseLog.logHeader(response);

                //解析，返回字符串
                if (data.listener != null) {
                    ResponseData responseData = new ResponseData();
                    responseData.body = response.body().string();
                    if (response.isSuccessful()) {
                        data.listener.onSuccess(responseData);
                    } else {
                        data.listener.onError(response.code(), "业务处理失败", responseData);
                    }
                }
            }
        });
    }

    private RequestBody create(Map<String, String> params) {
        FormBody.Builder mFormBodyBuilder = new FormBody.Builder();
        Set<Map.Entry<String, String>> set = params.entrySet();
        Iterator<Map.Entry<String, String>> iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            if (entry.getValue() != null) {
                mFormBodyBuilder.add(entry.getKey(), entry.getValue());
            }
        }
        return mFormBodyBuilder.build();
    }

    private RequestBody create(String body) {
        MediaType mt = MediaType.parse("application/json; charset=utf-8");
        return RequestBody.create(mt, body);
    }


}

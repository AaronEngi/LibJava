package com.github.aaronengi.http.okhttp.async;

import com.github.aaronengi.http.async.RequestData;
import com.github.aaronengi.http.async.RequestListener;
import com.github.aaronengi.http.async.ResponseData;

import org.greenrobot.eventbus.EventBus;

import okhttp3.OkHttpClient;
import tyrael.eventbus.event.Status401Event;
import tyrael.eventbus.event.Status403Event;

/**
 * Created by 王超 on 2017/11/13.
 */

public class HttpWithEvent extends HttpWithCookie {
    public HttpWithEvent(OkHttpClient okHttpClient) {
        super(okHttpClient);
    }

    @Override
    public void send(RequestData data) {
        System.out.println("HttpWithEvent send");
        RequestListener original = data.listener;
        data.listener = new RequestListener() {
            @Override
            public void onError(int codeLocal, String msg, ResponseData responseData) {
                original.onError(codeLocal, msg, responseData);
                if (codeLocal == 401) {
                    EventBus.getDefault().post(new Status401Event());
                }
                if (codeLocal == 403) {
                    EventBus.getDefault().post(new Status403Event());
                }
            }

            @Override
            public void onSuccess(ResponseData responseData) {
                original.onSuccess(responseData);
            }
        };
        sender.send(data);

    }
}

package com.github.aaronengi.http.okhttp.async;

import com.github.aaronengi.http.async.IHttpSender;
import com.github.aaronengi.http.async.RequestData;
import com.github.aaronengi.http.okhttpapi.CookieJarSupport;
import com.github.aaronengi.http.okhttpapi.OkSender;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import tyrael.data.http.Cookie;

/**
 * Created by 王超 on 2017/11/11.
 */

public class HttpWithCookie implements IHttpSender {
    protected final IHttpSender sender;
    private final OkHttpClient okHttpClient;

    public HttpWithCookie(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
        this.sender = new OkSender(okHttpClient);
    }

    @Override
    public void send(RequestData data) {
        System.out.println("HttpWithCookie send");
        sender.send(data);
    }

    public WebSocket newWebSocket(String wsUrl, WebSocketListener listener) {
        Request request = new Request.Builder()
                .url(wsUrl)
                .build();
        return okHttpClient.newWebSocket(request, listener);
    }

    public void clearCookie() {
        CookieJarSupport cookieJarSupport = (CookieJarSupport) okHttpClient.cookieJar();
        cookieJarSupport.clear();
    }

    public Cookie getCookie(String host, String key) {
        CookieJarSupport cookieJarSupport = (CookieJarSupport) okHttpClient.cookieJar();
        return cookieJarSupport.getCookie(host, key);
    }

    public void addCookie(String host, Cookie cookie) {
        CookieJarSupport cookieJarSupport = (CookieJarSupport) okHttpClient.cookieJar();
        cookieJarSupport.addCookie(host, cookie);
    }

}

package com.github.aaronengi.http.okhttp.async;

import com.github.aaronengi.http.async.RequestData;
import com.github.aaronengi.http.okhttpapi.ClientApi;

import okhttp3.OkHttpClient;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import tyrael.data.http.Cookie;

/**
 * Created by 王超 on 2017/11/11.
 */

public class HttpWithCookieStatic {
    private static HttpWithCookie httpWithCookie = new HttpWithEvent(ClientApi.getClientWithCookie());

    /**
     * 可以不调用，
     * 如果要调用，在第一个请求前调用。以后不要再调用了。
     */
    public static void init(OkHttpClient okHttpClient) {
        System.out.println("HttpWithCookieStatic init");
        httpWithCookie = new HttpWithEvent(okHttpClient);
    }

    public static void send(final RequestData data) {
        System.out.println("async HttpWithCookieStatic send");
        httpWithCookie.send(data);
    }

    public static WebSocket newWebSocket(String wsUrl, WebSocketListener listener) {
        return httpWithCookie.newWebSocket(wsUrl, listener);
    }

    public static void clearCookie() {
        httpWithCookie.clearCookie();
    }

    public static Cookie getCookie(String host, String key) {
        return httpWithCookie.getCookie(host, key);
    }

    public static void addCookie(String host, Cookie cookie) {
        httpWithCookie.addCookie(host, cookie);
    }
}

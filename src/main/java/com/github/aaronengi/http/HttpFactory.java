package com.github.aaronengi.http;

import com.github.aaronengi.http.HttpAdapter;

import java.net.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

public class HttpFactory {
    public static HttpAdapter createDefault() {
        HttpAdapter httpAdapter = new HttpAdapter();
        httpAdapter.setClient(new OkHttpClient());
        return httpAdapter;
    }

    public static HttpAdapter create(Proxy proxy) {
        HttpAdapter httpAdapter = new HttpAdapter();
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder().proxy(proxy)
                .build();
        httpAdapter.setClient(okHttpClient);
        return httpAdapter;
    }

    /**
     * 据说60秒，服务器会关闭连接
     * https://github.com/square/okhttp/issues/3113
     */
    private static final long PING_INTERVAL_MS = 50 * 1000;

    public static HttpAdapter createCookieEnabled() {
        HttpAdapter http;
        OkHttpClient okHttpClient;
        http = new HttpAdapter();
        okHttpClient = new OkHttpClient().newBuilder().cookieJar(new CookieJar() {
            private final Map<String, List<Cookie>> cookieStore = new HashMap<>();

            @Override
            public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                cookieStore.put(url.host(), cookies);
            }

            @Override
            public List<Cookie> loadForRequest(HttpUrl url) {
                List<Cookie> cookies = cookieStore.get(url.host());
                return cookies != null ? cookies : new ArrayList<Cookie>();
            }
        })
                .pingInterval(PING_INTERVAL_MS, TimeUnit.MILLISECONDS)
                .build();
        http.setClient(okHttpClient);
        return http;
    }
}

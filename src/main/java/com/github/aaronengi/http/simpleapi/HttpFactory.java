package com.github.aaronengi.http.simpleapi;

import com.github.aaronengi.http.HttpAdapter;

import java.net.Proxy;

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
}

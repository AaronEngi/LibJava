package com.github.aaronengi.http;

import com.github.aaronengi.http.okhttpapi.PersistCookieJar;

import java.net.Proxy;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import javax.annotation.Nullable;

import okhttp3.OkHttpClient;

public class HttpFactory {
    public static OkHttpClient.Builder defaultBuilder() {
        Duration timeOut = Duration.ofSeconds(60);
        return new OkHttpClient().newBuilder().connectTimeout(timeOut).readTimeout(timeOut).writeTimeout(timeOut);
    }

    public static OkHttpClient.Builder enableCookie(OkHttpClient.Builder builder, String cookieFilePath) {
        return builder.cookieJar(new PersistCookieJar(cookieFilePath))
                .pingInterval(PING_INTERVAL_MS, TimeUnit.MILLISECONDS);
    }

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

    public static HttpAdapter createCookieEnabled(@Nullable String cookieFilePath) {
        HttpAdapter http;
        OkHttpClient okHttpClient;
        http = new HttpAdapter();
        okHttpClient = defaultBuilder().cookieJar(new PersistCookieJar(cookieFilePath))
                .pingInterval(PING_INTERVAL_MS, TimeUnit.MILLISECONDS)
                .build();
        http.setClient(okHttpClient);
        return http;
    }
}

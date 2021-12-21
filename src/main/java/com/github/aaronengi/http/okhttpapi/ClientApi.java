package com.github.aaronengi.http.okhttpapi;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by wangchao on 2017/2/28.
 */

public class ClientApi {
    private static final long PING_INTERVAL_MS = (long) (50 * 1000);

    private static final OkHttpClient clientNoConfig = new OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build();

    private static final OkHttpClient clientWithCookie = new OkHttpClient().newBuilder()
            .cookieJar(new PersistCookieJar())
            .pingInterval(PING_INTERVAL_MS, TimeUnit.MILLISECONDS)
            .build();

    public static OkHttpClient getClientNoConfig() {
        return clientNoConfig;
    }

    public static OkHttpClient getClientWithCookie() {
        return clientWithCookie;
    }

    public static OkHttpClient.Builder enableSystemLog(OkHttpClient.Builder builder) {
        if (builder == null) {
            builder = new OkHttpClient.Builder();
        }
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request(); //Current Request
                Response response = chain.proceed(originalRequest); //Get response of the request
                //I am logging the response body in debug mode. When I do this I consume the response (OKHttp only lets you do this once) so i have re-build a new one using the cached body
                String bodyString = response.body().string();
                System.out.println(String.format("Sending request %s with headers %s ", originalRequest.url(), originalRequest.headers()));
                System.out.println(String.format("Got response HTTP %s %s \n\n with body %s \n\n with headers %s ",
                        response.code(), response.message(), bodyString, response.headers()));
                response = response.newBuilder().body(ResponseBody.create(response.body().contentType(), bodyString)).build();
                return response;
            }
        });
        return builder;
    }
}

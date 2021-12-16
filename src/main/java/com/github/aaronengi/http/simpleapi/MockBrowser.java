package com.github.aaronengi.http.simpleapi;

import com.github.aaronengi.http.HttpAdapter;
import com.github.aaronengi.http.HttpFactory;

import java.util.Map;

import okhttp3.Response;

/**
 * 封装通用的功能，大部分情况只要调用一个方法就可以发送请求了。
 * 少部分需要获取内部，进行配置，仅用于应急
 */
public class MockBrowser {
    private static final HttpAdapter http;

    static {
        http = HttpFactory.createDefault();
    }

    public static Response get(String url) {
        return http.get(url, null);
    }

    public static Response get(String url, Map<String, String> headers) {
        return http.get(url, headers);
    }

    public static Response post(String url, String json) {
        return http.post(url, json);
    }

    public static Response post(String url, String json, Map<String, String> header) {
        return http.post(url, json, header);
    }

    public static Response post(String url, Map<String, String> map) {
        return http.post(url, map);
    }
}

package com.github.aaronengi.http;

import java.util.Map;

import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

public class HttpWithCookieStatic {
    private static final HttpAdapter http = HttpFactory.createCookieEnabled(null);

    public static WebSocket newWebSocket(String wsUrl, WebSocketListener listener) {
        Request request = new Request.Builder()
                .url(wsUrl)
                .build();
        return http.getClient().newWebSocket(request, listener);
    }

    public static Response get(String url) {
        return http.get(url, null);
    }

    public static Response post(String url) {
        return http.post(url);
    }

    public static Response post(String url, String json) {
        return http.post(url, json);
    }

    public static Response post(String url, Map<String, String> map) {
        return http.post(url, map);
    }
}

package com.github.aaronengi.http.async;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangchao on 2017/3/7.
 */

public class RequestData {
    public String method;
    public String url;
    public Map<String, String> headers = new HashMap<>();
    public RequestListener listener;

    public Map<String, String> params;
    public String body;

    public RequestData(String url, RequestListener listener) {
        this.url = url;
        this.listener = listener;

        this.method = "GET";
    }

    public RequestData(String url, Map<String, String> params, RequestListener listener) {
        this.url = url;
        this.listener = listener;
        this.params = params;

        this.method = "POST";
    }
}

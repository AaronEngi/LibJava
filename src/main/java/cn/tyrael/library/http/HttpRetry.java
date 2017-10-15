package cn.tyrael.library.http;

import okhttp3.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpRetry {
    /**
     * 底层http
     */
    private HttpAdapter http;

    public HttpRetry(){
        http = new HttpAdapter();
        http.setClient(new OkHttpClient());
    }

    public Response get(String url) {
        return http.get(url);
    }

    public Response post(String url) {
        return http.post(url);
    }

    public Response post(String url, String json) {
        return http.post(url, json);
    }

    public Response post(String url, Map<String, String> map) {
        return http.post(url, map);
    }
}

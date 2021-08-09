package tyrael.http;

import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Response;

/**
 * 封装重试一次的逻辑
 */
public class HttpRetry {
    /**
     * 底层http
     */
    private HttpAdapter http;

    public HttpRetry(){
        http = new HttpAdapter();
//        OkHttpClient.Builder builder = new OkHttpClient.Builder().
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

    public Response post(String url, String json, Map<String, String> header) {
        return http.post(url, json, header);
    }

    public Response post(String url, Map<String, String> map) {
        return http.post(url, map);
    }

    public OkHttpClient getClient() {
        return http.getClient();
    }
}

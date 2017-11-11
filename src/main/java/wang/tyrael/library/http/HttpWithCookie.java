package wang.tyrael.library.http;

import okhttp3.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 封装了http cookie逻辑
 */
public class HttpWithCookie {
    /**
     * 据说60秒，服务器会关闭连接
     * https://github.com/square/okhttp/issues/3113
     */
    private static final long PING_INTERVAL_MS = (long) (50 * 1000);


	/**
	 * 底层http
	 */
	private HttpAdapter http;
	private OkHttpClient okHttpClient;

	public HttpWithCookie(){
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

	public WebSocket newWebSocket(String wsUrl, WebSocketListener listener) {
        Request request = new Request.Builder()
                .url(wsUrl)
                .build();
		return okHttpClient.newWebSocket(request, listener);
	}
}

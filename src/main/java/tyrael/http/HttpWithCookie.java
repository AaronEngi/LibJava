package tyrael.http;

import com.github.aaronengi.http.okhttpapi.PersistCookieJar;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

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
		okHttpClient = new OkHttpClient().newBuilder().cookieJar(new PersistCookieJar())
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

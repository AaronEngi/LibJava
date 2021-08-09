package tyrael.http;

import java.util.Map;

import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

public class HttpWithCookieStatic {
	private static HttpWithCookie http = new HttpWithCookie();

	public static WebSocket newWebSocket(String wsUrl, WebSocketListener listener){
		return http.newWebSocket(wsUrl, listener);
	}
	
	public static Response get(String url) {
		return http.get(url);
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

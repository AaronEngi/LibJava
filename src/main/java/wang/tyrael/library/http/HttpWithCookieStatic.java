package wang.tyrael.library.http;

import okhttp3.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpWithCookieStatic {
	private static HttpAdapter http;
	
	static{
		http = new HttpAdapter();
		http.setClient(new OkHttpClient().newBuilder().cookieJar(new CookieJar() {
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
		}).build());
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

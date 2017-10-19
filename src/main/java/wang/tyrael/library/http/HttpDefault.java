package wang.tyrael.library.http;

import okhttp3.OkHttpClient;
import okhttp3.Response;

public class HttpDefault {
	private static HttpAdapter http;
	
	static{
		http = new HttpAdapter();
		http.setClient(new OkHttpClient());
	}
	
	public static Response get(String url) {
		return http.get(url);
	}
	
	public static Response post(String url, String json) {
		return http.post(url, json);
	}
}

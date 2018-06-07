package wang.tyrael.http;

import okhttp3.Response;

import java.util.Map;

public class HttpDefault {
	private static HttpRetry http;
	
	static{
		http = new HttpRetry();
//		http.setClient(new OkHttpClient());
	}
	
	public static Response get(String url) {
		return http.get(url);
	}
	
	public static Response post(String url, String json) {
		return http.post(url, json);
	}

	public static Response post(String url, Map<String, String> map){
		return http.post(url, map);
	}
}

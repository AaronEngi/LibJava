package wang.tyrael.http.simpleapi;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import wang.tyrael.http.HttpAdapter;

import java.net.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * http�ͻ��˹���
 * @author Administrator
 *
 */
public class HttpFactory {
	public static HttpAdapter createDefault(){
		HttpAdapter httpAdapter = new HttpAdapter();
		httpAdapter.setClient(new OkHttpClient());
		return httpAdapter;
	}

	public static HttpAdapter create(Proxy proxy){
		HttpAdapter httpAdapter = new HttpAdapter();
		OkHttpClient okHttpClient = new OkHttpClient().newBuilder().proxy(proxy)
				.build();
		httpAdapter.setClient(okHttpClient);
		return httpAdapter;
	}
}

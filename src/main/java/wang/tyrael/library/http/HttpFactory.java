package wang.tyrael.library.http;

import okhttp3.OkHttpClient;

/**
 * http�ͻ��˹���
 * @author Administrator
 *
 */
public class HttpFactory {
	public static HttpAdapter getDefault(){
		HttpAdapter httpAdapter = new HttpAdapter();
		httpAdapter.setClient(new OkHttpClient());
		return httpAdapter;
	}
}

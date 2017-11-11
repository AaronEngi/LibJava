package wang.tyrael.library.http.okhttpapi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

/**
 * Created by wangchao on 2017/2/28.
 */

public class ClientApi {
    private static final long PING_INTERVAL_MS = (long) (50 * 1000);

    private static OkHttpClient clientNoConfig = new OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build();

    private static OkHttpClient clientWithCookie = new OkHttpClient().newBuilder()
            .cookieJar(new CookieJarSupport())
            .pingInterval(PING_INTERVAL_MS, TimeUnit.MILLISECONDS)
            .build();

    public static OkHttpClient getClientNoConfig() {
        return clientNoConfig;
    }

    public static OkHttpClient getClientWithCookie() {
        return clientWithCookie;
    }
}

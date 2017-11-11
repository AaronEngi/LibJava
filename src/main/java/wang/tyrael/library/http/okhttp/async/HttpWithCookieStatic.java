package wang.tyrael.library.http.okhttp.async;

import okhttp3.Cookie;
import okhttp3.OkHttpClient;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import wang.tyrael.library.http.async.RequestData;
import wang.tyrael.library.http.okhttpapi.ClientApi;

/**
 * Created by 王超 on 2017/11/11.
 */

public class HttpWithCookieStatic {
    private static HttpWithCookie httpWithCookie = new HttpWithCookie(ClientApi.getClientWithCookie());

    /**
     * 可以不调用，
     * 如果要调用，在第一个请求前调用。以后不要再调用了。
     */
    public static void init(OkHttpClient okHttpClient){
        System.out.print("HttpWithCookieStatic init");
        httpWithCookie = new HttpWithCookie(okHttpClient);
    }

    public static void send(final RequestData data){
        httpWithCookie.send(data);
    }

    public static WebSocket newWebSocket(String wsUrl, WebSocketListener listener){
        return httpWithCookie.newWebSocket(wsUrl, listener);
    }

    public static void clearCookie(){
        httpWithCookie.clearCookie();
    }

    public static Cookie getCookie(String host, String key){
        return httpWithCookie.getCookie(host, key);
    }

    public static void addCookie(String host, Cookie cookie){
        httpWithCookie.addCookie(host, cookie);
    }
}

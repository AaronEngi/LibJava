package tyrael.http.okhttp.async;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import tyrael.data.http.Cookie;
import tyrael.http.async.IHttpSender;
import tyrael.http.async.RequestData;
import tyrael.http.okhttpapi.CookieJarSupport;
import tyrael.http.okhttpapi.OkSender;

/**
 * Created by 王超 on 2017/11/11.
 */

public class HttpWithCookie implements IHttpSender {
    private final OkHttpClient okHttpClient ;
    protected final IHttpSender sender;

    public HttpWithCookie(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
        this.sender = new OkSender(okHttpClient);
    }

    @Override
    public void send(RequestData data) {
        System.out.println("HttpWithCookie send");
        sender.send(data);
    }

    public WebSocket newWebSocket(String wsUrl, WebSocketListener listener){
        Request request = new Request.Builder()
                .url(wsUrl)
                .build();
        return okHttpClient.newWebSocket(request, listener);
    }

    public void clearCookie(){
        CookieJarSupport cookieJarSupport = (CookieJarSupport) okHttpClient.cookieJar();
        cookieJarSupport.clear();
    }

    public Cookie getCookie(String host, String key){
        CookieJarSupport cookieJarSupport = (CookieJarSupport) okHttpClient.cookieJar();
        return cookieJarSupport.getCookie(host, key);
    }

    public void addCookie(String host, Cookie cookie){
        CookieJarSupport cookieJarSupport = (CookieJarSupport) okHttpClient.cookieJar();
        cookieJarSupport.addCookie(host, cookie);
    }

}

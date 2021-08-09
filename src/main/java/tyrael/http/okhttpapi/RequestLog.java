package tyrael.http.okhttpapi;

import okhttp3.Headers;
import okhttp3.Request;

/**
 * Created by 王超 on 2017/11/10.
 */

public class RequestLog {
    public static void logHeader(Request request){
        Headers headers = request.headers();
        System.out.println("request:" + request.url() + " header:");
        System.out.println(headers.toString());
    }
}

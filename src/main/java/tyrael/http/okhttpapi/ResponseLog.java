package tyrael.http.okhttpapi;


import okhttp3.Headers;
import okhttp3.Response;

/**
 * Created by 王超 on 2017/11/10.
 */

public class ResponseLog {
    public static void logHeader(Response response){
        Headers headers = response.headers();
        System.out.println("request:" + response.request().url() + " header:");
        System.out.println(headers.toString());
    }
}

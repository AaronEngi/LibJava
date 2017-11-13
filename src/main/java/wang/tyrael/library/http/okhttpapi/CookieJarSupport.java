package wang.tyrael.library.http.okhttpapi;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import wang.tyrael.eventbus.event.RememberMeEvent;

/**
 *
 * JSESSIONID
 * remember-me  需要持久化保存。
 * 这样，就免登录了。
 *
 * 后台优先使用JSESSIONID，其次使用remember-me
 *
 * 如果remember-me则会返回401
 *
 * Created by 王超 on 2017/11/11.
 */

public class CookieJarSupport implements CookieJar {
    private final Map<String, List<Cookie>> cookieStore = new HashMap<>();

    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        cookieStore.put(url.host(), cookies);

        Cookie rememberMe = null;
        Cookie session = null;
        for (Cookie cookie :
                cookies) {
            //如果关键词不是remember-me需要通过其他方式处理
            if(cookie.name().equals("remember-me")){
                rememberMe = cookie;
            }
            if(cookie.name().equals("JSESSIONID")){
                session = cookie;
            }
        }
        if(rememberMe != null){
            if(rememberMe.value().isEmpty()){
                return;
            }
            //目前的机制，只发送成功
            EventBus.getDefault().post(new RememberMeEvent(new wang.tyrael.data.http.Cookie(rememberMe), new wang.tyrael.data.http.Cookie(session)));
        }
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        List<Cookie> cookies = cookieStore.get(url.host());
        return cookies != null ? cookies : new ArrayList<Cookie>();
    }

    public void clear(){
        cookieStore.clear();
    }

    public wang.tyrael.data.http.Cookie getCookie(String host, String key){
        List<Cookie> list = cookieStore.get(host);
        if(list == null){
            return null;
        }
        for (Cookie cookie :
                list) {
            if(cookie.name().equals(key)){
                return new wang.tyrael.data.http.Cookie(cookie);
            }
        }
        return null;
    }

    public void addCookie(String host, wang.tyrael.data.http.Cookie cookie){
        List<Cookie> list = cookieStore.get(host);
        if(list == null){
            list = new ArrayList<>();
            cookieStore.put(host, list);
        }
        list.add(cookie.toOkhttpCookie());
    }
}

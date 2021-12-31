package com.github.aaronengi.http.okhttpapi;

import com.github.aaronengi.file.FileUtil;
import com.github.aaronengi.http.CookieData;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * JSESSIONID
 * remember-me  需要持久化保存。
 * 这样，就免登录了。
 * <p>
 * 后台优先使用JSESSIONID，其次使用remember-me
 * <p>
 * 如果remember-me则会返回401
 * <p>
 * Created by 王超 on 2017/11/11.
 */

@NotThreadSafe
public class PersistCookieJar implements CookieJar {
    //for Serializable
    private Map<String, List<CookieData>> cookieCache;
    @Nullable
    private final String persistFilePath;

    public PersistCookieJar() {
        persistFilePath = null;
    }

    public PersistCookieJar(String persistFilePath) {
        System.out.println("PersistCookieJar persistFilePath = " + persistFilePath);
        this.persistFilePath = persistFilePath;
    }

    @Override
    public void saveFromResponse(HttpUrl url, @Nonnull List<Cookie> cookies) {
//        System.out.println("saveFromResponse cookies = " + cookies);
        putOkHttpCookies(url.topPrivateDomain(), cookies);
    }

    @Override
    @Nonnull
    public List<Cookie> loadForRequest(HttpUrl url) {
        // TODO: 12/20/2021 match 的判断
        List<CookieData> cookies = getCacheForRead().get(url.topPrivateDomain());
//        System.out.println("loadForRequest:" + cookies);
        List<Cookie> result = new ArrayList<>();
        if (cookies != null) {
            for (CookieData cookie : cookies) {
                if (cookie.expiresAt > System.currentTimeMillis()) {
                    result.add(cookie.newOkhttpCookie());
                }
            }
        }
//        System.out.println("loadForRequest:" + result);
        return result;
    }


    public CookieData getCookie(String domain, String key) {
        List<CookieData> list = getCacheForRead().get(domain);
        if (list == null) {
            return null;
        }
        for (CookieData cookie : list) {
            if (cookie.name.equals(key)) {
                return cookie;
            }
        }
        return null;
    }

    //region persist
    private Map<String, List<CookieData>> getCacheForRead() {
        if (cookieCache == null) {
            checkRead();
        }
        return cookieCache;
    }

    private void putOkHttpCookies(String key, List<Cookie> value) {
        put(key, CookieData.newList(value));
    }

    private void put(String key, List<CookieData> value) {
        if (cookieCache == null) {
            //内存同步
            checkRead();
        }
        cookieCache.put(key, value);
        write();
    }

    public void addCookie(String domain, CookieData cookie) {
        List<CookieData> list = getCacheForRead().get(domain);
        if (list == null) {
            list = new ArrayList<>();
        }
        list.add(cookie);
        put(domain, list);
    }

    public void clear() {
        if (persistFilePath != null) {
            //noinspection ResultOfMethodCallIgnored
            new File(persistFilePath).delete();
        }
        cookieCache = null;
    }

    //只有初始化时，读取存储。可以延迟初始化
    private void checkRead() {
        if (cookieCache != null) {
            return;
        }
        cookieCache = FileUtil.readObject(persistFilePath);
        if (cookieCache == null) {
            cookieCache = new HashMap<>();
        }
    }

    private void write() {
        FileUtil.writeObject(persistFilePath, cookieCache);
    }
    //endregion
}

package com.github.aaronengi.http;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

/**
 * okhttp 的cookie 不支持json序列化
 * Created by 王超 on 2017/11/11.
 */

public class CookieData implements Serializable {
    public static final long serialVersionUID = 1L;

    public String name;
    public String value;
    public long expiresAt;
    public String domain;
    public String path;
    public boolean secure;
    public boolean httpOnly;
    public boolean persistent;
    public boolean hostOnly;

    public CookieData() {
    }

    public CookieData(okhttp3.Cookie cookie) {
        name = cookie.name();
        value = cookie.value();
        expiresAt = cookie.expiresAt();
        domain = cookie.domain();
        path = cookie.path();
        secure = cookie.secure();
        httpOnly = cookie.httpOnly();
        persistent = cookie.persistent();
        hostOnly = cookie.hostOnly();
    }

    public okhttp3.Cookie newOkhttpCookie() {
        okhttp3.Cookie.Builder builder = new okhttp3.Cookie.Builder();
        builder.expiresAt(expiresAt);
        builder.name(name);
        builder.value(value);
        if (hostOnly) {
            builder.hostOnlyDomain(domain);
        } else {
            builder.domain(domain);
        }
        builder.path(path);
        if (secure) {
            builder.secure();
        }
        if (httpOnly) {
            builder.httpOnly();
        }
        return builder.build();
    }

    @Nonnull
    public static List<CookieData> newList(@Nonnull List<okhttp3.Cookie> cookies) {
        List<CookieData> result = new ArrayList<>();
        for (okhttp3.Cookie c : cookies) {
            result.add(new CookieData(c));
        }
        return result;
    }

    @Nonnull
    public static List<okhttp3.Cookie> newOkhttpList(@Nonnull List<CookieData> cookies) {
        List<okhttp3.Cookie> result = new ArrayList<>();
        for (CookieData c : cookies) {
            result.add(c.newOkhttpCookie());
        }
        return result;
    }

}

package tyrael.data.http;

/**
 *
 * okhttp 的cookie 不支持json序列化
 * Created by 王超 on 2017/11/11.
 */

public class Cookie {
   public String name;
   public String value;
   public long expiresAt;
   public String domain;


    public Cookie() {
    }

    public Cookie(okhttp3.Cookie cookie) {
        name = cookie.name();
        value = cookie.value();
        expiresAt = cookie.expiresAt();
        domain = cookie.domain();
    }

    public okhttp3.Cookie toOkhttpCookie(){
        okhttp3.Cookie.Builder builder = new okhttp3.Cookie.Builder();
        builder.expiresAt(expiresAt);
        builder.name(name);
        builder.value(value);
        builder.domain(domain);

        return builder.build();
    }
}

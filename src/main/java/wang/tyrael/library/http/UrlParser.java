package wang.tyrael.library.http;

import java.net.MalformedURLException;
import java.net.URL;

public class UrlParser {
    public final String sUrl;
    public final URL url;

    public UrlParser(String sUrl) throws MalformedURLException {
        this.sUrl = sUrl;
        this.url = new URL(sUrl);
    }

    /**
     *
     * @return 域名的第一部分
     */
    public String getFirstOfHost(){
        String host = url.getHost();
        int i = host.indexOf(".");
        return host.substring(0, i);
    }

    public URL getUrl(){
        return url;
    }
}

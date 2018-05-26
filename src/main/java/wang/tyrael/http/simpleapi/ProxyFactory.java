package wang.tyrael.http.simpleapi;

import java.net.InetSocketAddress;
import java.net.Proxy;

/**
 * @author: wangchao
 * 2018/5/26 0026
 */
public class ProxyFactory {
    public Proxy createHttp(String ip, int port){
        return new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ip, port));
    }
}

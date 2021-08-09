package tyrael.http.async;

/**
 * 对底层框架的抽象
 * Created by csmallTech on 2017/3/6.
 */
public interface IHttpSender {
   void send(RequestData data);
}

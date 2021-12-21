package tyrael.eventbus.event;


import com.github.aaronengi.http.CookieData;

/**
 * Created by 王超 on 2017/11/11.
 */

public class RememberMeEvent {
    public final CookieData rememberMe;
    public final CookieData session;

    public RememberMeEvent(CookieData rememberMe, CookieData session) {
        this.rememberMe = rememberMe;
        this.session = session;
    }
}

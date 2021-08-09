package tyrael.eventbus.event;


import tyrael.data.http.Cookie;

/**
 * Created by 王超 on 2017/11/11.
 */

public class RememberMeEvent {
    public final Cookie rememberMe;
    public final Cookie session;

    public RememberMeEvent(Cookie rememberMe, Cookie session) {
        this.rememberMe = rememberMe;
        this.session = session;
    }
}

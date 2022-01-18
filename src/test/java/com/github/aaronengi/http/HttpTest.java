package com.github.aaronengi.http;

import org.junit.jupiter.api.Test;

public class HttpTest {
    @Test
    public void cookie(){
        HttpAdapter httpAdapter = HttpFactory.createCookieEnabled("./cache/test");
        httpAdapter.get("https://zoom.us", null);
        httpAdapter.get("https://zoom.us/2", null);
    }
}

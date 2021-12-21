package com.github.aaronengi.http;

import org.junit.Test;

public class HttpTest {
    @Test
    public void cookie(){
        HttpAdapter httpAdapter = HttpFactory.createCookieEnabled("c:/SourceCode/cache/test");
        httpAdapter.get("https://zoom.us", null);
        httpAdapter.get("https://zoom.us/2", null);
    }
}

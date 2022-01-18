package com.github.aaronengi.net;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class IPAddressUtilTest {
    @Test
    public void isMyAddress() {
        List<String> trueCases = Arrays.asList(
                "2001:559:a4:21e:d1b6:e0a7:8956:bad8",
                "2001:559:a4:21e:d1b6:e0a7:8956:baD8",
                "192.168.56.1"
                );
        List<String> falseCases = Arrays.asList(
                "2001:559:a4:21e:d1b6:e0a7:8956:bad7",
                "192.168.56.0",
                "::",
                ":",
                "",
                null,
                "1",
                "a",
                "sfasdfas",
                "2001:559:a4:21e:d1b6:e0a7:8956:",
                "2001:559:a4:21e:d1b6:e0a7::",
                "192.168.56.",
                "192.168.."
        );

        trueCases.forEach(string->{
            boolean isMyAddress = IPAddressUtil.isMyAddress(string);
            assert isMyAddress;
            System.out.println("string = " + string);
            System.out.println("isMyAddress = " + isMyAddress);
            System.out.println();
        });

        falseCases.forEach(string->{
            boolean isMyAddress = IPAddressUtil.isMyAddress(string);
            assert !isMyAddress;
            System.out.println("string = " + string);
            System.out.println("isMyAddress = " + isMyAddress);
            System.out.println();
        });
    }
}

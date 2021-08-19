package com.github.aaronengi.net;

import com.google.common.net.InetAddresses;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.List;

import javax.annotation.Nullable;

public class IPAddressUtil {
    public static boolean isMyAddress(@Nullable String ipAddress) {
        if (ipAddress == null) {
            return false;
        }
        InetAddress inetAddress;
        try {
            //noinspection UnstableApiUsage
            inetAddress = InetAddresses.forString(ipAddress);
        } catch (IllegalArgumentException e) {
//            e.printStackTrace();
            return false;
        }

        try {
            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface networkInterface : interfaces) {
                List<InetAddress> addresses = Collections.list(networkInterface.getInetAddresses());
                if(addresses.contains(inetAddress)){
                    return true;
                }
            }
        } catch (SocketException e) {
//            e.printStackTrace();
        }
        return false;
    }
}

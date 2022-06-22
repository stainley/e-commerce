package com.salapp.ecommerce.api.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
@Component
public class ServiceUtil {

    private String serviceAddress = null;

    public String getServiceAddress() {
        if(serviceAddress == null) {
            serviceAddress = findMyHostname() + "/" + findMyIpAddress();
        }
        return serviceAddress;
    }

    private String findMyHostname() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            return "unknown host name";
        }
    }

    private String findMyIpAddress() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            return "unknown IP Address";
        }
    }
}

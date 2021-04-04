package com.rbondarovich.web.utils;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static com.rbondarovich.web.utils.HttpHeader.HTTP_CLIENT_IP;
import static com.rbondarovich.web.utils.HttpHeader.HTTP_X_FORWARDED_FOR;
import static com.rbondarovich.web.utils.HttpHeader.PROXY_CLIENT_IP;
import static com.rbondarovich.web.utils.HttpHeader.WL_PROXY_CLIENT_IP;
import static com.rbondarovich.web.utils.HttpHeader.X_FORWARDED_FOR;

@Component
public class RequestFilter {

    public String getRemoteIpFrom(HttpServletRequest request) {
        String ip = null;
        int tryCount = 1;

        while (!isIpFound(ip) && tryCount <= 6) {
            switch (tryCount) {
                case 1:
                    ip = request.getHeader(X_FORWARDED_FOR.key());
                    break;
                case 2:
                    ip = request.getHeader(PROXY_CLIENT_IP.key());
                    break;
                case 3:
                    ip = request.getHeader(WL_PROXY_CLIENT_IP.key());
                    break;
                case 4:
                    ip = request.getHeader(HTTP_CLIENT_IP.key());
                    break;
                case 5:
                    ip = request.getHeader(HTTP_X_FORWARDED_FOR.key());
                    break;
                default:
                    ip = request.getRemoteAddr();
            }

            tryCount++;
        }

        return ip;
    }

    private boolean isIpFound(String ip) {
        return ip != null && ip.length() > 0 && !"unknown".equalsIgnoreCase(ip);
    }
}

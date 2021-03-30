package com.rbondarovich.web.utils;

public enum HttpHeader {
    X_FORWARDED_FOR("X-Forwarded-For"),
    PROXY_CLIENT_IP("Proxy-Client-IP"),
    WL_PROXY_CLIENT_IP("WL-Proxy-Client-IP"),
    HTTP_CLIENT_IP("HTTP_CLIENT_IP"),
    HTTP_X_FORWARDED_FOR("HTTP_X_FORWARDED_FOR");

    private String key;

    HttpHeader(String key) {
        this.key = key;
    }

    public String key() {
        return this.key;
    }
}

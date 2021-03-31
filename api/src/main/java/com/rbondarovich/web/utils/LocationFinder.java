package com.rbondarovich.web.utils;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CountryResponse;
import com.maxmind.geoip2.record.Country;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.HashSet;
import java.util.Set;

import static com.rbondarovich.web.utils.HttpHeader.HTTP_CLIENT_IP;
import static com.rbondarovich.web.utils.HttpHeader.HTTP_X_FORWARDED_FOR;
import static com.rbondarovich.web.utils.HttpHeader.PROXY_CLIENT_IP;
import static com.rbondarovich.web.utils.HttpHeader.WL_PROXY_CLIENT_IP;
import static com.rbondarovich.web.utils.HttpHeader.X_FORWARDED_FOR;


@Component
public class LocationFinder {

    private DatabaseReader dbReader;

    public void createDatabaseReader() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File database = new File(classLoader.getResource("IP_DB.mmdb").getFile());
        this.dbReader = new DatabaseReader.Builder(database).build();
    }

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

    public Set<String> getCountryByIp(String ip) throws IOException, GeoIp2Exception {
        if (this.dbReader == null) createDatabaseReader();

        Set<String> countryNames = new HashSet<>();

        InetAddress ipAddress = InetAddress.getByName(ip);
        CountryResponse response = this.dbReader.country(ipAddress);

        Country country = response.getCountry();
        countryNames.add(country.getName());
        countryNames.add(country.getNames().get("ru"));

        return countryNames;
    }
}

package com.rbondarovich.service.utils;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CountryResponse;
import com.maxmind.geoip2.record.Country;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Set;


@Component
public class LocationFinder {

    private DatabaseReader dbReader;

    public void createDatabaseReader() {
        ClassLoader classLoader = getClass().getClassLoader();
        try {
            InputStream database = classLoader.getResourceAsStream("IP_DB.mmdb");
            this.dbReader = new DatabaseReader.Builder(database).build();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Set<String> getCountryByIp(String ip) {
        if (this.dbReader == null) createDatabaseReader();

        Set<String> countryNames = new HashSet<>();

        try {
            InetAddress ipAddress = InetAddress.getByName(ip);
            CountryResponse response = this.dbReader.country(ipAddress);
            Country country = response.getCountry();
            countryNames.add(country.getName());
            countryNames.add(country.getNames().get("ru"));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (GeoIp2Exception e) {
            e.printStackTrace();
        }

        return countryNames;
    }
}

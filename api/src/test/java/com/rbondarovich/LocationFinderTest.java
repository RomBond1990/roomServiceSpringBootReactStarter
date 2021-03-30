package com.rbondarovich;

import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.rbondarovich.web.utils.LocationFinder;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Set;

public class LocationFinderTest {

    private final String IP = "37.214.49.20";

    @Test
    public void givenIpShouldReturnCountryBelarus() throws IOException, GeoIp2Exception {
        LocationFinder locationFinder = new LocationFinder();
        locationFinder.createDatabaseReader();
        Set<String> countryNames = locationFinder.getCountryByIp(IP);
        Assert.assertTrue(countryNames.contains("Беларусь"));
        Assert.assertTrue(countryNames.contains("Belarus"));
    }
}

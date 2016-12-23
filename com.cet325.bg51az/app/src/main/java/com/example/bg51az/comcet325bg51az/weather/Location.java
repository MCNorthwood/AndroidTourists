package com.example.bg51az.comcet325bg51az.weather;

import java.io.Serializable;

/**
 * Created by MCNorthwood on 23/12/2016.
 */
public class Location implements Serializable {
    private float lon;
    private float lat;
    private String country;
    private String city;

    public float getLongitude() {
        return lon;
    }
    public void setLongitude(float longitude) {
        this.lon = longitude;
    }
    public float getLatitude() {
        return lat;
    }
    public void setLatitude(float latitude) {
        this.lat = latitude;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
}

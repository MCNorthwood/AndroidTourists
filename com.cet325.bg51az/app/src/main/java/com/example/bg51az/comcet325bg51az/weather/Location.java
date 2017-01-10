package com.example.bg51az.comcet325bg51az.weather;

import java.io.Serializable;

public class Location implements Serializable {
    private float lon;
    private float lat;
    private long sunset;
    private long sunrise;
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

    public long getSunset() { return sunset; }
    public void setSunset(long sunset) { this.sunset = sunset; }

    public long getSunrise() { return sunrise; }
    public void setSunrise(long sunrise) { this.sunrise = sunrise; }

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

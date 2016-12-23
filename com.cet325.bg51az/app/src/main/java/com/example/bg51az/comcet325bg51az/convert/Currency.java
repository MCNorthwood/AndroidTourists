package com.example.bg51az.comcet325bg51az.convert;

import java.io.Serializable;

/**
 * Created by MCNorthwood on 23/12/2016.
 */
public class Currency {//implements Serializable {
    private double GBP;
    private double EUR;
    private double USD;
    private double locale;
    private double favourite;

    public double getGBP() { return GBP; }
    public void setGBP(double GBP) { this.GBP = GBP; }
    public double getEUR() { return EUR; }
    public void setEUR(double EUR) { this.EUR = EUR; }
    public double getUSD() { return USD; }
    public void setUSD(double USD) { this.USD = USD; }
    public double getLocale() { return locale; }
    public void setLocale(double locale) { this.locale = locale; }
    public double getFavourite() { return favourite; }
    public void setfavourite(double favourite) { this.favourite = favourite; }
    /*private String name;
    private double rate;

    public Currency(String name, double rate){
        this.name = name;
        this.rate = rate;
    }

    public String getName(){return name;}
    public double getRate(){return rate;}*/
}

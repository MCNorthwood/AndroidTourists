package com.example.bg51az.comcet325bg51az.convert;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.Locale;

public class CurrencyExchange
{
    private String base;
    private String date;
    private String fav;
    private double favourite;
    private double GBP; // Great British Pound £
    private double EUR; // Euro €
    private double USD; // United States Dollar $
    private double AUD; // Australian Dollar $
    private double BGN; // Bulgarian Lev лв
    private double BRL; // Brazilian Real R$
    private double CAD; // Canadian Dollar $
    private double CHF; // Swiss Franc CHF
    private double CNY; // Chinese Yuan ¥
    private double CZK; // Czech Koruna Kč
    private double DKK; // Danish Krone kr
    private double HKD; // Hong Kong Dollar $
    private double HRK; // Croatian Kuna kn
    private double HUF; // Hungarian Forint Ft
    private double IDR; // Indonesian Rupiah
    private double ILS; // Israeli Shekel ₪
    private double INR; // Indian Rupee
    private double JPY; // Japanese Yen ¥
    private double KRW; // Korean Won ₩
    private double MXN; // Mexican Peso $
    private double MYR; // Malaysian Ringgit RM
    private double NOK; // Norwegian Krone kr
    private double NZD; // New Zealand Dollar $
    private double PHP; // Philippine Peso ₱
    private double PLN; // Polish Zloty zł
    private double RON; // Romanian New Leu lei
    private double RUB; // Russian Rouble ₽
    private double SEK; // Swedish Krona kr
    private double SGD; // Singapore Dollar $
    private double THB; // Thai Baht ฿
    private double TRY; // Turkish Lira
    private double ZAR; // South African Rand R

    public CurrencyExchange(){
        setFav("EUR");
    }

    public double convertCurrency(double base, double exchange){
        double convert = base * exchange;
        return roundDouble(convert);
    }

    private double roundDouble(double round){
        BigDecimal bd = new BigDecimal(round);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public String getSymbol(String local){
        Locale locale;
        Currency curr;
        if(local.contains("EUR")){
            locale = Locale.FRANCE;
            curr = Currency.getInstance(locale);
            local = curr.getSymbol(locale);
        }
        if(local.contains("USD")){
            locale = Locale.US;
            curr = Currency.getInstance(locale);
            local = curr.getSymbol(locale);
        }
        if(local.contains("JPY")){
            locale = Locale.JAPAN;
            curr = Currency.getInstance(locale);
            local = curr.getSymbol(locale);
        }
        if(local.contains("CNY")){
            locale = Locale.CHINA;
            curr = Currency.getInstance(locale);
            local = curr.getSymbol(locale);
        }
        if(local.contains("KRW")){
            locale = Locale.KOREA;
            curr = Currency.getInstance(locale);
            local = curr.getSymbol(locale);
        }

        return local;
    }

    // gets and sets the date it was retrieved
    public String getBase() {
        return base;
    }
    public void setBase(String base) {
        this.base = base;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public String getFav() { return fav; }
    public void setFav(String fav) { this.fav = fav; }

    public double getFavourite() { return favourite; }
    public void setFavourite(double favourite) { this.favourite = favourite; }

    public double getGBP() { return GBP; }
    public void setGBP(double GBP) { this.GBP = GBP; }

    //gets and set Euro and Dollars as the Base is in GBP
    public double getEUR() {
        return EUR;
    }
    public void setEUR(double EUR) {
        this.EUR = EUR;
    }

    public double getUSD() {
        return USD;
    }
    public void setUSD(double USD) {
        this.USD = USD;
    }

    public double getAUD() {
        return AUD;
    }
    public void setAUD(double AUD) {
        this.AUD = AUD;
    }

    public double getBGN() {
        return BGN;
    }
    public void setBGN(double BGN) {
        this.BGN = BGN;
    }

    public double getBRL() {
        return BRL;
    }
    public void setBRL(double BRL) {
        this.BRL = BRL;
    }

    public double getCAD() {
        return CAD;
    }
    public void setCAD(double CAD) {
        this.CAD = CAD;
    }

    public double getCHF() {
        return CHF;
    }
    public void setCHF(double CHF) {
        this.CHF = CHF;
    }

    public double getCNY() {
        return CNY;
    }
    public void setCNY(double CNY) {
        this.CNY = CNY;
    }

    public double getCZK() {
        return CZK;
    }
    public void setCZK(double CZK) {
        this.CZK = CZK;
    }

    public double getDKK() {
        return DKK;
    }
    public void setDKK(double DKK) {
        this.DKK = DKK;
    }

    public double getHKD() {
        return HKD;
    }
    public void setHKD(double HKD) {
        this.HKD = HKD;
    }

    public double getHRK() {
        return HRK;
    }
    public void setHRK(double HRK) {
        this.HRK = HRK;
    }

    public double getHUF() {
        return HUF;
    }
    public void setHUF(double HUF) {
        this.HUF = HUF;
    }

    public double getIDR() {
        return IDR;
    }
    public void setIDR(double IDR) {
        this.IDR = IDR;
    }

    public double getILS() {
        return ILS;
    }
    public void setILS(double ILS) {
        this.ILS = ILS;
    }

    public double getINR() {
        return INR;
    }
    public void setINR(double INR) {
        this.INR = INR;
    }

    public double getJPY() {
        return JPY;
    }
    public void setJPY(double JPY) {
        this.JPY = JPY;
    }

    public double getKRW() {
        return KRW;
    }
    public void setKRW(double KRW) {
        this.KRW = KRW;
    }

    public double getMXN() {
        return MXN;
    }
    public void setMXN(double MXN) {
        this.MXN = MXN;
    }

    public double getMYR() {
        return MYR;
    }
    public void setMYR(double MYR) {
        this.MYR = MYR;
    }

    public double getNOK() {
        return NOK;
    }
    public void setNOK(double NOK) {
        this.NOK = NOK;
    }

    public double getNZD() {
        return NZD;
    }
    public void setNZD(double NZD) {
        this.NZD = NZD;
    }

    public double getPHP() {
        return PHP;
    }
    public void setPHP(double PHP) {
        this.PHP = PHP;
    }

    public double getPLN() {
        return PLN;
    }
    public void setPLN(double PLN) {
        this.PLN = PLN;
    }

    public double getRON() {
        return RON;
    }
    public void setRON(double RON) {
        this.RON = RON;
    }

    public double getRUB() {
        return RUB;
    }
    public void setRUB(double RUB) {
        this.RUB = RUB;
    }

    public double getSEK() {
        return SEK;
    }
    public void setSEK(double SEK) {
        this.SEK = SEK;
    }

    public double getSGD() {
        return SGD;
    }
    public void setSGD(double SGD) {
        this.SGD = SGD;
    }

    public double getTHB() {
        return THB;
    }
    public void setTHB(double THB) {
        this.THB = THB;
    }

    public double getTRY() {
        return TRY;
    }
    public void setTRY(double TRY) {
        this.TRY = TRY;
    }

    public double getZAR() {
        return ZAR;
    }
    public void setZAR(double ZAR) {
        this.ZAR = ZAR;
    }
}
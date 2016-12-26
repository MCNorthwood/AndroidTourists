package com.example.bg51az.comcet325bg51az.convert;

import org.json.JSONException;
import org.json.JSONObject;

public class CurrencyParser {
    public static Currency getCurrency(String data) throws JSONException {
        Currency curr = new Currency();

        // create a JSON object from the data provided
        JSONObject jObj = new JSONObject(data);

        curr.setBase(getString("base", jObj));
        curr.setDate(getString("date", jObj));

        // get the rates of each country
        JSONObject rObj = getObject("rates",jObj);
        curr.setEUR(getDouble("EUR", rObj));
        curr.setUSD(getDouble("USD", rObj));
        curr.setAUD(getDouble("AUD", rObj));
        curr.setBGN(getDouble("BGN", rObj));
        curr.setBRL(getDouble("BRL", rObj));
        curr.setCAD(getDouble("CAD", rObj));
        curr.setCHF(getDouble("CHF", rObj));
        curr.setCNY(getDouble("CNY", rObj));
        curr.setCZK(getDouble("CZK", rObj));
        curr.setDKK(getDouble("DKK", rObj));
        curr.setHKD(getDouble("HKD", rObj));
        curr.setHRK(getDouble("HRK", rObj));
        curr.setHUF(getDouble("HUF", rObj));
        curr.setIDR(getDouble("IDR", rObj));
        curr.setILS(getDouble("ILS", rObj));
        curr.setINR(getDouble("INR", rObj));
        curr.setJPY(getDouble("JPY", rObj));
        curr.setKRW(getDouble("KRW", rObj));
        curr.setMXN(getDouble("MXN", rObj));
        curr.setMYR(getDouble("MYR", rObj));
        curr.setNOK(getDouble("NOK", rObj));
        curr.setNZD(getDouble("NZD", rObj));
        curr.setPHP(getDouble("PHP", rObj));
        curr.setPLN(getDouble("PLN", rObj));
        curr.setRON(getDouble("RON", rObj));
        curr.setRUB(getDouble("RUB", rObj));
        curr.setSEK(getDouble("SEK", rObj));
        curr.setSGD(getDouble("SGD", rObj));
        curr.setTHB(getDouble("THB", rObj));
        curr.setTRY(getDouble("TRY", rObj));
        curr.setZAR(getDouble("ZAR", rObj));

        return curr;
    }

    private static JSONObject getObject(String name, JSONObject Obj) throws JSONException{
        JSONObject subObj = Obj.getJSONObject(name);
        return  subObj;
    }

    private static String getString(String name, JSONObject Obj) throws JSONException{
        return Obj.getString(name);
    }

    private static double getDouble(String name, JSONObject Obj) throws JSONException{
        return Obj.getDouble(name);
    }
}

package com.example.bg51az.comcet325bg51az.convert;

import org.json.JSONException;
import org.json.JSONObject;

public class CurrencyParser {
    public static Currency getCurrency(String data) throws JSONException {
        Currency curr = new Currency();

        // create a JSON object from the data provided
        JSONObject jObj = new JSONObject(data);

        curr.base =  jObj.getString("base");
        curr.date = jObj.getString("date");

        // get the rates of each country
        JSONObject rObj = getObject("rates",jObj);
        //if(curr.base != "GBP") {
            curr.GBP = rObj.getDouble("GBP");
        //}
        //curr.EUR = rObj.getDouble("EUR");
        curr.USD = rObj.getDouble("USD");
        curr.AUD = rObj.getDouble("AUD");
        curr.BGN = rObj.getDouble("BGN");
        curr.BRL = rObj.getDouble("BRL");
        curr.CAD = rObj.getDouble("CAD");
        curr.CHF = rObj.getDouble("CHF");
        curr.CNY = rObj.getDouble("CNY");
        curr.CZK = rObj.getDouble("CZK");
        curr.DKK = rObj.getDouble("DKK");
        curr.HKD = rObj.getDouble("HKD");
        curr.HRK = rObj.getDouble("HRK");
        curr.HUF = rObj.getDouble("HUF");
        curr.IDR = rObj.getDouble("IDR");
        curr.ILS = rObj.getDouble("ILS");
        curr.INR = rObj.getDouble("INR");
        curr.JPY = rObj.getDouble("JPY");
        curr.KRW = rObj.getDouble("KRW");
        curr.MXN = rObj.getDouble("MXN");
        curr.MYR = rObj.getDouble("MYR");
        curr.NOK = rObj.getDouble("NOK");
        curr.NZD = rObj.getDouble("NZD");
        curr.PHP = rObj.getDouble("PHP");
        curr.PLN = rObj.getDouble("PLN");
        curr.RON = rObj.getDouble("RON");
        curr.RUB = rObj.getDouble("RUB");
        curr.SEK = rObj.getDouble("SEK");
        curr.SGD = rObj.getDouble("SGD");
        curr.THB = rObj.getDouble("THB");
        curr.TRY = rObj.getDouble("TRY");
        curr.ZAR = rObj.getDouble("ZAR");

        return curr;
    }

    private static JSONObject getObject(String name, JSONObject Obj) throws JSONException{
        JSONObject subObj = Obj.getJSONObject(name);
        return  subObj;
    }
}

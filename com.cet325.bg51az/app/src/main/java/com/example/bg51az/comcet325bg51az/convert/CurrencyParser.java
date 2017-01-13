package com.example.bg51az.comcet325bg51az.convert;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class CurrencyParser
{
    public static CurrencyExchange getCurrency(String data) throws JSONException
    {
        CurrencyExchange curr = new CurrencyExchange();

        // create a JSON object from the data provided
        JSONObject jObj = new JSONObject(data);

        curr.setBase(jObj.getString("base"));
        curr.setDate(jObj.getString("date"));

        /*Get the exchange rate of each country and then sets the value.
         If the option to change the base is added means that no code would
          need to change here. */
        JSONObject rObj = getObject("rates",jObj);
        if(!curr.getBase().contains("GBP")) { curr.setGBP(getDouble("GBP", rObj)); }
        if(!curr.getBase().contains("EUR")) { curr.setEUR(getDouble("EUR", rObj)); }
        if(!curr.getBase().contains("USD")) { curr.setUSD(getDouble("USD", rObj)); }
        if(!curr.getBase().contains("AUD")) { curr.setAUD(getDouble("AUD", rObj)); }
        if(!curr.getBase().contains("BGN")) { curr.setBGN(getDouble("BGN", rObj)); }
        if(!curr.getBase().contains("BRL")) { curr.setBRL(getDouble("BRL", rObj)); }
        if(!curr.getBase().contains("CAD")) { curr.setCAD(getDouble("CAD", rObj)); }
        if(!curr.getBase().contains("CHF")) { curr.setCHF(getDouble("CHF", rObj)); }
        if(!curr.getBase().contains("CNY")) { curr.setCNY(getDouble("CNY", rObj)); }
        if(!curr.getBase().contains("CZK")) { curr.setCZK(getDouble("CZK", rObj)); }
        if(!curr.getBase().contains("DKK")) { curr.setDKK(getDouble("DKK", rObj)); }
        if(!curr.getBase().contains("HKD")) { curr.setHKD(getDouble("HKD", rObj)); }
        if(!curr.getBase().contains("HRK")) { curr.setHRK(getDouble("HRK", rObj)); }
        if(!curr.getBase().contains("HUF")) { curr.setHUF(getDouble("HUF", rObj)); }
        if(!curr.getBase().contains("IDR")) { curr.setIDR(getDouble("IDR", rObj)); }
        if(!curr.getBase().contains("ILS")) { curr.setILS(getDouble("ILS", rObj)); }
        if(!curr.getBase().contains("INR")) { curr.setINR(getDouble("INR", rObj)); }
        if(!curr.getBase().contains("JPY")) { curr.setJPY(getDouble("JPY", rObj)); }
        if(!curr.getBase().contains("KRW")) { curr.setKRW(getDouble("KRW", rObj)); }
        if(!curr.getBase().contains("MXN")) { curr.setMXN(getDouble("MXN", rObj)); }
        if(!curr.getBase().contains("MYR")) { curr.setMYR(getDouble("MYR", rObj)); }
        if(!curr.getBase().contains("NOK")) { curr.setNOK(getDouble("NOK", rObj)); }
        if(!curr.getBase().contains("NZD")) { curr.setNZD(getDouble("NZD", rObj)); }
        if(!curr.getBase().contains("PHP")) { curr.setPHP(getDouble("PHP", rObj)); }
        if(!curr.getBase().contains("PLN")) { curr.setPLN(getDouble("PLN", rObj)); }
        if(!curr.getBase().contains("RON")) { curr.setRON(getDouble("RON", rObj)); }
        if(!curr.getBase().contains("RUB")) { curr.setRUB(getDouble("RUB", rObj)); }
        if(!curr.getBase().contains("SEK")) { curr.setSEK(getDouble("SEK", rObj)); }
        if(!curr.getBase().contains("SGD")) { curr.setSGD(getDouble("SGD", rObj)); }
        if(!curr.getBase().contains("THB")) { curr.setTHB(getDouble("THB", rObj)); }
        if(!curr.getBase().contains("TRY")) { curr.setTRY(getDouble("TRY", rObj)); }
        if(!curr.getBase().contains("ZAR")) { curr.setZAR(getDouble("ZAR", rObj)); }

        Log.d("Currency", curr.toString());

        return curr;
    }

    private static JSONObject getObject(String name, JSONObject Obj) throws JSONException
    {
        JSONObject subObj = Obj.getJSONObject(name);
        return  subObj;
    }

    private static double getDouble(String name, JSONObject jsonObject) throws JSONException
    {
        return jsonObject.getDouble(name);
    }
}
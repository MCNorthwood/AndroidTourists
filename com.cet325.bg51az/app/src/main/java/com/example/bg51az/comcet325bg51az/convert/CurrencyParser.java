package com.example.bg51az.comcet325bg51az.convert;

import org.json.JSONException;
import org.json.JSONObject;

public class CurrencyParser {
    public static Currency getCurrency(String data) throws JSONException{
        Currency curr = new Currency();

        // create a JSON object from the data provided
        JSONObject jObj = new JSONObject(data);


        return curr;
    }
}

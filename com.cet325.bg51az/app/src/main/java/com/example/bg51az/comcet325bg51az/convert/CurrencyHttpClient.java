package com.example.bg51az.comcet325bg51az.convert;

import android.util.Log;

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CurrencyHttpClient {

    private static String BASE_URL = "http://api.fixer.io/latest?base=GBP";

    public String getCurrencyData() {
        HttpURLConnection con = null;
        InputStream iS = null;

        try {
            con = (HttpURLConnection) (new URL(BASE_URL)).openConnection(); //establish connection to API
            con.setRequestMethod("GET");
            con.setDoInput(true);
            con.setDoOutput(true);
            con.connect();

            int response = con.getResponseCode();
            if (response == HttpURLConnection.HTTP_OK) { // If there is a response, proceed
                //read response
                StringBuffer buff = new StringBuffer();
                iS = con.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(iS));
                String line = null;
                while ((line = br.readLine()) != null) {
                    buff.append(line + "\r\n");
                }
                iS.close();
                con.disconnect();
                return buff.toString();
            } else {
                Log.d("HttpURLConnection", "Failed to connect");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            ;
        } finally {
            try {
                iS.close();
            } catch (Exception e) {
            }
            try {
                con.disconnect();
            } catch (Exception e) {
            }
        }

        return null;
    }
}

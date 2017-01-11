package com.example.bg51az.comcet325bg51az.convert;

import android.util.Log;

import java.net.URL;
import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;

public class CurrencyHttpClient
{

    private static String BASE_URL = "http://api.fixer.io/";
    private static String END_URL = "latest";

    public String getCurrencyData()
    {
        HttpURLConnection con = null;
        InputStream iS = null;
        String urlString = "";

        try
        {
            urlString = BASE_URL + URLEncoder.encode(END_URL, "UTF-8") + "?base=GBP";
            Log.d("urlString", urlString);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        try
        {
            con = (HttpURLConnection) (new URL(urlString)).openConnection(); //establish connection to API
            con.setRequestMethod("GET");
            con.connect();

            int response = con.getResponseCode();
            if (response == HttpURLConnection.HTTP_OK)
            { // If there is a response, proceed
                //read response
                StringBuffer buff = new StringBuffer();
                iS = con.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(iS));
                String line;
                while ((line = br.readLine()) != null) {
                    buff.append(line + "\r\n");
                }
                iS.close();
                con.disconnect();
                return buff.toString();
            }
            else
            {
                Log.d("HttpURLConnection", "Failed to connect");
                return null;
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }
}
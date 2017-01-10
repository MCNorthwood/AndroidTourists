package com.example.bg51az.comcet325bg51az.weather;

import android.util.Log;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class WeatherHttpClient {

    private static String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?q=";
    private static String IMG_URL = "http://openweathermap.org/img/w/";
    private static String API_KEY = "e3bca88164cff50b89c3e24593594476";

    public String getWeatherData(String location) {
        HttpURLConnection con = null;
        InputStream iS = null;
        String urlString = "";

        try {
            //create URL for specified city, metric units and 7 lines to return
            urlString = BASE_URL + URLEncoder.encode(location, "UTF-8") + "&units=metric&APPID=" + API_KEY;
            Log.d("urlString", urlString);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        try {
            con = (HttpURLConnection) (new URL(urlString)).openConnection();
            con.setRequestMethod("GET");
            con.setDoInput(true);
            con.setDoOutput(true);
            con.connect();

            //check response
            int response = con.getResponseCode();
            if (response == HttpURLConnection.HTTP_OK) {
                //Read the response
                StringBuffer buffer = new StringBuffer();
                iS = con.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(iS));
                String line = null;
                while ((line = br.readLine()) != null) {
                    buffer.append(line + "\r\n");
                }
                iS.close();
                con.disconnect();
                return buffer.toString();
            } else {
                Log.d("HttpURLConnection", "Connection failed");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    public byte[] getImage(String code) {
        HttpURLConnection con = null;
        InputStream iS = null;

        //get connection
        try {
            con = (HttpURLConnection) (new URL(IMG_URL + code + ".png")).openConnection();
            con.setRequestMethod("GET");
            con.setDoInput(true);
            con.connect();

            //Read the response
            iS = con.getInputStream();
            byte[] buffer = new byte[1024];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            while (iS.read(buffer) != -1) {
                baos.write(buffer);
            }

            return baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
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

package com.example.bg51az.comcet325bg51az.weather;


import org.json.JSONException;
import org.json.JSONObject;

public class WeatherParser {

    public static Weather getWeather(String data) throws JSONException{
        Weather weather = new Weather();

        //Create JSONObject from data passed in
        JSONObject jObj = new JSONObject(data);

        //extra data
        Location location = new Location();

        //City info
        JSONObject cityObj = getObject("city", jObj);
        location.setCity(getString("name", cityObj));
        location.setCountry(getString("country",cityObj));

        //Co-ordinates
        JSONObject coordObj = getObject("coord",cityObj);
        location.setLatitude(getFloat("lat", coordObj));
        location.setLongitude(getFloat("lon", coordObj));

        return weather;
    }

    private static JSONObject getObject(String name, JSONObject jsonObject) throws JSONException{
        JSONObject getObj = jsonObject.getJSONObject(name);
        return getObj;
    }

    private static String getString(String name, JSONObject jsonObject) throws JSONException{
        return jsonObject.getString(name);
    }

    private static float getFloat(String name, JSONObject jsonObject) throws JSONException{
        return (float)jsonObject.getDouble(name);
    }

    private static int getInt(String name, JSONObject jsonObject)throws JSONException{
        return  jsonObject.getInt(name);
    }
}

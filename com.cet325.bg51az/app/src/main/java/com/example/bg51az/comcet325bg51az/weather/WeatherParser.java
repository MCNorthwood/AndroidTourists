package com.example.bg51az.comcet325bg51az.weather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WeatherParser
{

    public static Weather getWeather(String data) throws JSONException
    {
        Weather weather = new Weather();

        //Create JSONObject from data passed in
        JSONObject jObj = new JSONObject(data);

        //extra data
        Location location = new Location();

        //Co-ordinates
        JSONObject coordObj = getObject("coord", jObj);
        location.setLatitude(getFloat("lat", coordObj));
        location.setLongitude(getFloat("lon", coordObj));

        JSONObject systemObj = getObject("sys", jObj);
        location.setCountry(getString("country", systemObj));
        location.setSunrise(getInt("sunrise", systemObj));
        location.setSunset(getInt("sunset",systemObj));
        location.setCity(getString("name", jObj));
        weather.location = location;

        // Get List Array info
        JSONArray jArr = jObj.getJSONArray("weather");

        // get first value
        JSONObject JSONWeather = jArr.getJSONObject(0);
        weather.weatherConditions.setWeatherId(getInt("id", JSONWeather));
        weather.weatherConditions.setMain(getString("main", JSONWeather));
        weather.weatherConditions.setDesc(getString("description", JSONWeather));
        weather.weatherConditions.setIcon(getString("icon", JSONWeather));

        JSONObject mainObj = getObject("main", jObj);
        weather.weatherConditions.setHumidity(getInt("humidity", mainObj));
        weather.weatherConditions.setPressure(getInt("pressure", mainObj));
        weather.temperature.setMaxTemp(getFloat("temp_max", mainObj));
        weather.temperature.setMinTemp(getFloat("temp_min", mainObj));
        weather.temperature.setTemp(getFloat("temp", mainObj));

        // Wind
        JSONObject wObj = getObject("wind", jObj);
        weather.wind.setSpeed(getFloat("speed",wObj));
        weather.wind.setDeg(getFloat("deg",wObj));

        // Clouds
        JSONObject cObj = getObject("clouds", jObj);
        weather.clouds.setPerc(getInt("all", cObj));

        // Rain
        //JSONObject rObj = getObject("rain", jObj);
        //weather.rain.setAmount(getFloat("3h",rObj));

        return weather;
    }

    private static JSONObject getObject(String name, JSONObject jsonObject) throws JSONException
    {
        JSONObject getObj = jsonObject.getJSONObject(name);
        return getObj;
    }

    private static String getString(String name, JSONObject jsonObject) throws JSONException
    {
        return jsonObject.getString(name);
    }

    private static float getFloat(String name, JSONObject jsonObject) throws JSONException
    {
        return (float) jsonObject.getDouble(name);
    }

    private static int getInt(String name, JSONObject jsonObject) throws JSONException
    {
        return jsonObject.getInt(name);
    }
}
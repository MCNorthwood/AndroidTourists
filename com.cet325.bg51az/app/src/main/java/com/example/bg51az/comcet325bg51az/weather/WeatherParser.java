package com.example.bg51az.comcet325bg51az.weather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WeatherParser {

    public static Weather getWeather(String data) throws JSONException {
        Weather weather = new Weather();

        //Create JSONObject from data passed in
        JSONObject jObj = new JSONObject(data);

        //extra data
        Location location = new Location();

        //City info
        JSONObject cityObj = getObject("city", jObj);
        location.setCity(getString("name", cityObj));
        location.setCountry(getString("country", cityObj));

        //Co-ordinates
        JSONObject coordObj = getObject("coord", cityObj);
        location.setLatitude(getFloat("lat", coordObj));
        location.setLongitude(getFloat("lon", coordObj));
        weather.location = location;

        // Get List Array info
        JSONArray jArr = jObj.getJSONArray("list");

        // Get 1st value in array
        JSONObject dObj = jArr.getJSONObject(0);
        weather.weatherConditions.setDt(getString("dt", dObj));

        // get 2nd object in array (Temperature)
        JSONObject mainObj = jArr.getJSONObject(1);
        weather.temperature.setTemp(getFloat("temp", mainObj));
        weather.temperature.setMinTemp(getFloat("temp_min", mainObj));
        weather.temperature.setMaxTemp(getFloat("temp_max", mainObj));
        weather.weatherConditions.setPressure(getInt("pressure", mainObj));
        weather.weatherConditions.setHumidity(getInt("humidity", mainObj));

        // Get weather Array within the List Array
        JSONArray _jArr = jArr.getJSONArray(2);
        JSONObject JSONWeather = _jArr.getJSONObject(0);
        weather.weatherConditions.setWeatherId(getInt("id", JSONWeather));
        weather.weatherConditions.setMain(getString("main", JSONWeather));
        weather.weatherConditions.setDesc(getString("description", JSONWeather));
        weather.weatherConditions.setIcon(getString("icon", JSONWeather));

        // Clouds
        JSONObject cObj = jArr.getJSONObject(3);
        weather.clouds.setPerc(getInt("all", cObj));

        // Wind
        JSONObject wObj = jArr.getJSONObject(4);
        weather.wind.setSpeed(getFloat("speed",wObj));
        weather.wind.setDeg(getFloat("deg",wObj));

        // Rain
        JSONObject rObj = jArr.getJSONObject(5);
        weather.rain.setAmount(getFloat("3h",rObj));

        return weather;
    }

    private static JSONObject getObject(String name, JSONObject jsonObject) throws JSONException {
        JSONObject getObj = jsonObject.getJSONObject(name);
        return getObj;
    }

    private static String getString(String name, JSONObject jsonObject) throws JSONException {
        return jsonObject.getString(name);
    }

    private static float getFloat(String name, JSONObject jsonObject) throws JSONException {
        return (float) jsonObject.getDouble(name);
    }

    private static int getInt(String name, JSONObject jsonObject) throws JSONException {
        return jsonObject.getInt(name);
    }
}

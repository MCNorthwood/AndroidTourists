package com.example.bg51az.comcet325bg51az;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bg51az.comcet325bg51az.weather.Weather;
import com.example.bg51az.comcet325bg51az.weather.WeatherHttpClient;
import com.example.bg51az.comcet325bg51az.weather.WeatherParser;

import org.json.JSONException;

public class WeatherActivity extends AppCompatActivity implements View.OnClickListener
{
    private TextView cityText;
    private TextView condDesc;
    private TextView temp;
    private TextView pressure;
    private TextView humidity;
    private TextView windSpeed;
    private TextView windDeg;
    private TextView clouds;
    //private TextView rain;

    private ImageView imgView;

    EditText cityEdit;
    EditText countryEdit;

    Button btnStartWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        cityText = (TextView) findViewById(R.id.cityText);
        condDesc = (TextView) findViewById(R.id.condDesc);
        temp = (TextView) findViewById(R.id.temp);
        pressure = (TextView) findViewById(R.id.pressureInput);
        humidity = (TextView) findViewById(R.id.humidityInput);
        windSpeed = (TextView) findViewById(R.id.windSpeed);
        windDeg = (TextView) findViewById(R.id.windDeg);
        clouds = (TextView) findViewById(R.id.cloudiness);
        //rain = (TextView) findViewById(R.id.rainInput);

        imgView = (ImageView) findViewById(R.id.condIcon);

        cityEdit = (EditText) findViewById(R.id.editCity);
        countryEdit = (EditText) findViewById(R.id.editCountry);
        btnStartWeather = (Button)findViewById(R.id.btnWeatherAPI);
        btnStartWeather.setOnClickListener(this);

        // As the default city is london, when the it's first click and onCreate will load London weather
        String city = "London, United Kingdom";
        Initialise(city);
    }

    @Override
    public void onClick(View v)
    {
        int id = v.getId();

        String city = cityEdit.getText().toString();
        String country = countryEdit.getText().toString();

        if(id == R.id.btnWeatherAPI){ // Option to change the city to anywhere in the world
            String place = city + "," + country;
            Initialise(place);
        }
    }

    void Initialise(String city)
    {
        JSONWeatherTask task = new JSONWeatherTask();
        task.execute(new String[]{city});
    }

    private class JSONWeatherTask extends AsyncTask<String, Void, Weather>
    {
        @Override
        protected Weather doInBackground(String... params)
        {
            Weather weather = new Weather();

            String data = ((new WeatherHttpClient()).getWeatherData(params[0]));
            if (data == null)
            {
                return null;
            }
            else
            {
                try
                {
                    weather = WeatherParser.getWeather(data);
                    //retrieve the icon
                    weather.iconData = new WeatherHttpClient().getImage(weather.weatherConditions.getIcon());
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
                return weather;
            }
        }

        @Override
        protected void onPostExecute(Weather weather)
        {
            super.onPostExecute(weather);
            if(weather!=null)
            {
                if(weather.iconData != null && weather.iconData.length > 0)
                {
                    Bitmap img = BitmapFactory.decodeByteArray(weather.iconData, 0, weather.iconData.length);

                    if(img != null)
                    {
                        Log.d("img", img.toString());
                        imgView.setImageBitmap(img);
                    }

                    cityText.setText(weather.location.getCity() + ", " + weather.location.getCountry());
                    condDesc.setText(weather.weatherConditions.getMain() + "(" + weather.weatherConditions.getDesc() + ")");
                    temp.setText("Temp: " + Math.round((weather.temperature.getTemp())) + "°C" +
                            " Min: " + Math.round(weather.temperature.getMinTemp()) + "°C" +
                            " Max: " + Math.round(weather.temperature.getMaxTemp()) + "°C");
                    humidity.setText(" " + weather.weatherConditions.getHumidity() + "%");
                    pressure.setText(" " + weather.weatherConditions.getPressure() + " hPa");
                    windSpeed.setText(" " + weather.wind.getSpeed() + " km/h");
                    windDeg.setText(" " + weather.wind.getDeg() + "°");
                    clouds.setText(" " + weather.clouds.getPerc() + "%");
                    //rain.setText(" " + weather.rain.getAmount() + "%");
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Unable to retrive Weather data", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
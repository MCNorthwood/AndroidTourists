package com.example.bg51az.comcet325bg51az.weather;

/**
 * Created by MCNorthwood on 23/12/2016.
 */
public class Weather {
    public Location location;
    public WeatherCondition weatherConditions = new WeatherCondition();
    public Temperature temperature = new Temperature();

    public class WeatherCondition{
        private int weatherId;
        private String main;
        private String desc;
        private String icon;

        private float pressure;
        private float humidity;

        public int getWeatherId() {
            return weatherId;
        }
        public void setWeatherId(int weatherId) {
            this.weatherId = weatherId;
        }
        public String getMain() {
            return main;
        }
        public void setMain(String main) {
            this.main = main;
        }
        public String getDesc() { return desc; }
        public void setDesc(String desc) {
            this.desc = desc;
        }
        public String getIcon() {
            return icon;
        }
        public void setIcon(String icon) {
            this.icon = icon;
        }
        public float getPressure() {
            return pressure;
        }
        public void setPressure(float pressure) {
            this.pressure = pressure;
        }
        public float getHumidity() {
            return humidity;
        }
        public void setHumidity(float humidity) {
            this.humidity = humidity;
        }
    }

    public class Temperature{
        private float minTemp;
        private float maxTemp;
        private float tempDay;
        private float tempNight;
        private float tempEve;
        private float tempMorn;

        public float getMinTemp() { return minTemp; }
        public void setMinTemp(float minTemp) { this.minTemp = minTemp; }
        public float getMaxTemp() { return maxTemp; }
        public void setMaxTemp(float maxTemp) { this.maxTemp = maxTemp; }
        public float getTempDay() { return tempDay; }
        public void setTempDay(float tempDay) { this.tempDay = tempDay; }
        public float getTempNight() { return tempNight; }
        public void setTempNight(float tempNight) { this.tempNight = tempNight; }
        public float getTempEve() { return tempEve; }
        public void setTempEve(float tempEve) { this.tempEve = tempEve; }
        public float getTempMorn() { return tempMorn; }
        public void setTempMorn(float tempMorn){ this.tempMorn = tempMorn; }
    }

    public class Wind{
        private float speed;
        private float deg;

        public float getSpeed() { return speed; }
        public void setSpeed(float speed) { this.speed = speed; }
        public float getDeg() { return deg; }
        public void setDeg(float deg) { this.deg = deg; }
    }

    public class Clouds{
        private int perc;

        public int getPerc() { return perc; }
        public void setPerc(int perc) { this.perc = perc; }
    }
}

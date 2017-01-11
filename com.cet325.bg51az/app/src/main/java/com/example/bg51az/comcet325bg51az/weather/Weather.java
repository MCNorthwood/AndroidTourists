package com.example.bg51az.comcet325bg51az.weather;

public class Weather
{
    public Location location;
    public WeatherCondition weatherConditions = new WeatherCondition();
    public Temperature temperature = new Temperature();
    public Wind wind = new Wind();
    public Clouds clouds = new Clouds();
    public Rain rain = new Rain();

    public byte[] iconData;

    public class WeatherCondition
    {
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

        public String getDesc() {
            return desc;
        }
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

    public class Temperature
    {
        private float minTemp;
        private float maxTemp;
        private float temp;

        public float getMinTemp() {
            return minTemp;
        }
        public void setMinTemp(float minTemp) {
            this.minTemp = minTemp;
        }

        public float getMaxTemp() {
            return maxTemp;
        }
        public void setMaxTemp(float maxTemp) {
            this.maxTemp = maxTemp;
        }

        public float getTemp() {
            return temp;
        }
        public void setTemp(float tempDay) {
            this.temp = tempDay;
        }
    }

    public class Wind
    {
        private float speed;
        private float deg;

        public float getSpeed() {
            return speed;
        }
        public void setSpeed(float speed) {
            this.speed = speed;
        }

        public float getDeg() {
            return deg;
        }
        public void setDeg(float deg) {
            this.deg = deg;
        }
    }

    public class Clouds
    {
        private int perc;

        public int getPerc() {
            return perc;
        }
        public void setPerc(int perc) {
            this.perc = perc;
        }
    }

    public class Rain
    {
        private float amount;

        public float getAmount() { return amount; }
        public void setAmount(float amount) { this.amount = amount; }
    }
}
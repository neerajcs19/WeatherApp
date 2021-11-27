package com.example.weatherapp.data;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Data class containing all weather data parsed from the OpenWeather JSON
 */
public class LocalWeather {
    private String raw;
    private JSONObject jsonObject;

    private String weather;
    private String icon;


    private String temp;
    private String pressure;
    private String humidity;

    private String speed;
    private String deg;

    private String all;
    private String sunrise;
    private String sunset;

    private String timezone;
    private String name;

    /**
     * Parses the JSON, writing all available values.
     */
    public void parse() {
        if(this.raw != null && this.jsonObject != null) {
            try {
                String description = jsonObject.getString("coord");


                JSONArray weather = new JSONArray(jsonObject.getString("weather"));

                JSONObject zero = new JSONObject(weather.getString(0));
                this.weather = zero.getString("description");
                this.icon = zero.getString("icon");


                JSONObject main = new JSONObject(jsonObject.getString("main"));
                this.temp = main.getString("temp");
                this.pressure = main.getString("pressure");
                this.humidity = main.getString("humidity");


                JSONObject wind = new JSONObject(jsonObject.getString("wind"));
                this.speed = wind.getString("speed");
                try {
                    this.deg = wind.getString("deg");
                }
                catch(Exception f) {
                    f.printStackTrace();
                    Log.e("USER","DEG IS NULL");
                }

                JSONObject clouds = new JSONObject(jsonObject.getString("clouds"));
                this.all = clouds.getString("all");
                JSONObject sys = new JSONObject(jsonObject.getString("sys"));
                this.sunrise = sys.getString("sunrise");
                this.sunset = sys.getString("sunset");

                this.timezone = jsonObject.getString("timezone");
                this.name = jsonObject.getString("name");



                System.out.println(description);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


    public String getWeather() {
        return weather;
    }
    public String getIcon() {
        return icon;
    }
    public String getTemp() {
        return temp;
    }
    public String getHumidity() {
        return humidity;
    }
    public String getSpeed() {
        return speed;
    }

    public String getDeg() {
        return deg;
    }

    public String getAll() {
        return all;
    }

    public String getSunrise() {
        return sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public String getTimezone() {
        return timezone;
    }

    public String getName() {
        return name;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public void setRaw(String raw) {
        this.raw = raw;
    }

    public String getPressure() {
        return pressure;
    }

    public void setDeg(String deg) {
        this.deg = deg;
    }
    public void setName(String name) {
        this.name = name;
    }

}

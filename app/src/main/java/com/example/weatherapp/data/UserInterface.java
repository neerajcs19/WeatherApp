package com.example.weatherapp.data;

import android.widget.TextView;

/**
 * Data class containing all TextViews from the user interface.
 */
public class UserInterface {
    TextView windDescription;
    TextView cloudinessDescription;
    TextView pressureDescription;
    TextView humidityDescription;
    TextView sunriseDescription;
    TextView sunsetDescription;


    TextView temperature;
    TextView weather;

    public TextView getWeather() {
        return weather;
    }

    public void setWeather(TextView weather) {
        this.weather = weather;
    }

    public TextView getTemperature() {
        return temperature;
    }

    public void setTemperature(TextView temperature) {
        this.temperature = temperature;
    }

    public TextView getWindDescription() {
        return windDescription;
    }

    public void setWindDescription(TextView windDescription) {
        this.windDescription = windDescription;
    }

    public TextView getCloudinessDescription() {
        return cloudinessDescription;
    }

    public void setCloudinessDescription(TextView cloudinessDescription) {
        this.cloudinessDescription = cloudinessDescription;
    }


    public TextView getPressureDescription() {
        return pressureDescription;
    }

    public void setPressureDescription(TextView pressureDescription) {
        this.pressureDescription = pressureDescription;
    }


    public TextView getHumidityDescription() {
        return humidityDescription;
    }

    public void setHumidityDescription(TextView humidityDescription) {
        this.humidityDescription = humidityDescription;
    }

    public TextView getSunriseDescription() {
        return sunriseDescription;
    }

    public void setSunriseDescription(TextView sunriseDescription) {
        this.sunriseDescription = sunriseDescription;
    }

    public TextView getSunsetDescription() {
        return sunsetDescription;
    }

    public void setSunsetDescription(TextView sunsetDescription) {
        this.sunsetDescription = sunsetDescription;
    }
}


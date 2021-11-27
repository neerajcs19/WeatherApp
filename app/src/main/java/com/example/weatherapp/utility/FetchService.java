package com.example.weatherapp.utility;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.weatherapp.functionality.Functionality;
import com.example.weatherapp.data.Global;
import com.example.weatherapp.R;

/**
 * Basic FetchService to fetch data for every minute in the background
 * if the app is not in the foreground
 */
public class FetchService extends IntentService {


    /**
     * Set a name for the service
     */
    public FetchService() {
        super("A");
    }


    /**
     * The thread. Fetches the data every minute from the OpenWeatherMap server
     */
    private static class CustomThread extends Thread {
        public void run() {
            while(true) {
                try {
                    // check hourly for critical weather data
                    // 60000 = 60 secs = 1 min
                    // 60000*60 = 3600 = 60 mins =
                    sleep(60000);
                    TextView textView = Global.a.findViewById(R.id.currentLocationTextField);
                    String city2 = textView.getText().toString();
                    Log.i("City: ",city2);
                    Functionality.fetch(Global.a,city2);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Starts the custom thread
     * @param intent the intent
     */
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        // Hanover acts as placeholder name, should be removed

        CustomThread c = new CustomThread();
        c.start();

    }
}

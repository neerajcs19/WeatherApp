package com.example.weatherapp.functionality;

import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.weatherapp.R;
import com.example.weatherapp.data.Global;
import com.example.weatherapp.data.LocalWeather;
import com.example.weatherapp.data.UserInterface;
import com.example.weatherapp.utility.CustomCalendar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Functionality is implemented here
 */
public class Functionality {


    // all icons are from openweathermap.org
    /**
     * Sets the new weather icon for an ImageView
     *
     * @param s String which is a code representing the weather icon
     * @param icon1 Image view to be set to the new icon
     */
    public static void setIcon(String s, ImageView icon1) {
        if(s.equals("01d")) {
            icon1.setImageResource(R.drawable.a01d);
        }
        else if(s.equals("02d")) {
            icon1.setImageResource(R.drawable.a02d);
        }
        else if(s.equals("03d")) {
            icon1.setImageResource(R.drawable.a03d);
        }
        else if(s.equals("04d")) {
            icon1.setImageResource(R.drawable.a04d);
        }
        else if(s.equals("09d")) {
            icon1.setImageResource(R.drawable.a09d);
        }
        else if(s.equals("10d")) {
            icon1.setImageResource(R.drawable.a10d);
        }
        else if(s.equals("11d")) {
            icon1.setImageResource(R.drawable.a11d);
        }
        else if(s.equals("13d")) {
            icon1.setImageResource(R.drawable.a13d);
        }
        else if(s.equals("50d")) {
            icon1.setImageResource(R.drawable.a50d);
        }
        else if(s.equals("01n")) {
            icon1.setImageResource(R.drawable.a01n);
        }
        else if(s.equals("02n")) {
            icon1.setImageResource(R.drawable.a02n);
        }
        else if(s.equals("03n")) {
            icon1.setImageResource(R.drawable.a03n);
        }
        else if(s.equals("04n")) {
            icon1.setImageResource(R.drawable.a04n);
        }
        else if(s.equals("09n")) {
            icon1.setImageResource(R.drawable.a09n);
        }
        else if(s.equals("10n")) {
            icon1.setImageResource(R.drawable.a10n);
        }
        else if(s.equals("11n")) {
            icon1.setImageResource(R.drawable.a11n);
        }
        else if(s.equals("13n")) {
            icon1.setImageResource(R.drawable.a13n);
        }
        else if(s.equals("50n")) {
            icon1.setImageResource(R.drawable.a50n);
        }

    }


    /**
     * Fetches the JSON file from OpenWeatherMap then to be parsed
     * so weather data can be displayed.
     * @param a AppCompatActivity
     * @param city The city to be searched
     */
    public static void fetch(AppCompatActivity a, String city) {

        // when the app is started
        if(Global.first) {
            city = "Hanover";
            Global.first = false;
        }

        // secret API key!!!
        String key = a.getText(R.string.openweatherapi).toString();

// Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(a);
        String url =
            "https://api.openweathermap.org/data/2.5/weather?q="+city+"&units=metric&appid="+key;
//        textView3.setText(url);

        String url2 =
            "https://api.openweathermap.org/data/2.5/forecast?q="+city+"&units=metric&appid="+key;


        final ScrollView scroll2 = a.findViewById(R.id.scroll2);
        final TextView forecastBar = a.findViewById(R.id.forecastBar);


        final LocalWeather localWeather = new LocalWeather();

        // Handle labels on startup
        final UserInterface userInterface = new UserInterface();

        final AppCompatActivity b = a;

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, response -> {
            // Display the first 500 characters of the response string.
//                        textView2.setText(response);
            localWeather.setRaw(response);

            // now parse the JSON file, then change the user interface accordingly
            // first change the displayed data about the current weather.
            try {

                localWeather.setJsonObject(new JSONObject(response));
                localWeather.parse();

                ImageView imageView2 = b.findViewById(R.id.imageView2);
                imageView2.requestFocus();
                // handle weather icon
                String icon = localWeather.getIcon();


                Functionality.setIcon(icon,imageView2);

                TextView temperature = b.findViewById(R.id.temperature);
                userInterface.setTemperature(temperature);
                userInterface.getTemperature().setText(localWeather.getTemp() + " °C");

                TextView weather = b.findViewById(R.id.weather);
                userInterface.setWeather(weather);
                userInterface.getWeather().setText(localWeather.getWeather());


                TextView windDescription = b.findViewById(R.id.windDescription);
                userInterface.setWindDescription(windDescription);
                userInterface.getWindDescription().setText(localWeather.getSpeed()+" m/s, "+localWeather.getDeg()+" °");

                // TODO: Short description for degrees (N/NE/E/SE/S/SW/W/NW)
                // TODO: Handle null values

                TextView cloudinessDescription = b.findViewById(R.id.cloudinessDescription);
                userInterface.setCloudinessDescription(cloudinessDescription);
                userInterface.getCloudinessDescription().setText(localWeather.getAll()+ "%");

                TextView pressureDescription = b.findViewById(R.id.pressureDescription);
                userInterface.setPressureDescription(pressureDescription);
                userInterface.getPressureDescription().setText(localWeather.getPressure()+" hpa");

                TextView humidityDescription = b.findViewById(R.id.humidityDescription);
                userInterface.setHumidityDescription(humidityDescription);
                userInterface.getHumidityDescription().setText(localWeather.getHumidity()+" %");

                TextView sunriseDescription = b.findViewById(R.id.sunriseDescription);
                userInterface.setSunriseDescription(sunriseDescription);

                long settime = 0;
                long offset = 0;


                if(localWeather.getTimezone() != null) {
                    if(localWeather.getSunrise() != null) {
                        offset = Long.parseLong(localWeather.getTimezone());
                        long risetime = Long.parseLong(localWeather.getSunrise());
                        String s1 = CustomCalendar.calculateClockTimeFromUTC(risetime+offset);
                        userInterface.getSunriseDescription().setText(s1);
                    }
                }
                if(localWeather.getSunset() != null) {
                    settime = Long.parseLong(localWeather.getSunset());
                    TextView sunsetDescription = b.findViewById(R.id.sunsetDescription);
                    userInterface.setSunsetDescription(sunsetDescription);
                }

                String s2 = CustomCalendar.calculateClockTimeFromUTC(settime+offset);
                if(localWeather.getTimezone() != null) {
                    if(localWeather.getSunrise() != null) {
                        if(localWeather.getSunset() != null) {
                            userInterface.getSunsetDescription().setText(s2);
                        }
                    }
                }

            }
            catch (JSONException e) {
                e.printStackTrace();
            }

        }, Throwable::printStackTrace);







        // Change all data about the weather for every 3 hours
        int[] clockArray = new int[] {
                R.id.clock0, R.id.clock1, R.id.clock2, R.id.clock3, R.id.clock4, 
                R.id.clock5, R.id.clock6, R.id.clock7, R.id.clock8, R.id.clock9,
                R.id.clock10, R.id.clock11, R.id.clock12, R.id.clock13, R.id.clock14,
                R.id.clock15, R.id.clock16, R.id.clock17, R.id.clock18, R.id.clock19,
                R.id.clock20, R.id.clock21, R.id.clock22, R.id.clock23, R.id.clock24,
                R.id.clock25, R.id.clock26, R.id.clock27, R.id.clock28, R.id.clock29,
                R.id.clock30, R.id.clock31, R.id.clock32, R.id.clock33, R.id.clock34,
                R.id.clock35, R.id.clock36, R.id.clock37
        };
        
        int[] dateArray = new int[] {
                R.id.date0, R.id.date1, R.id.date2, R.id.date3, R.id.date4,
                R.id.date5, R.id.date6, R.id.date7, R.id.date8, R.id.date9,
                R.id.date10, R.id.date11, R.id.date12, R.id.date13, R.id.date14,
                R.id.date15, R.id.date16, R.id.date17, R.id.date18, R.id.date19,
                R.id.date20, R.id.date21, R.id.date22, R.id.date23, R.id.date24,
                R.id.date25, R.id.date26, R.id.date27, R.id.date28, R.id.date29,
                R.id.date30, R.id.date31, R.id.date32, R.id.date33, R.id.date34,
                R.id.date35, R.id.date36, R.id.date37
        };


        int[] importantArray = new int[] {
                R.id.important0, R.id.important1, R.id.important2, R.id.important3,
                R.id.important4, R.id.important5, R.id.important6, R.id.important7,
                R.id.important8, R.id.important9, R.id.important10, R.id.important11,
                R.id.important12, R.id.important13, R.id.important14, R.id.important15,
                R.id.important16, R.id.important17, R.id.important18, R.id.important19,
                R.id.important20, R.id.important21, R.id.important22, R.id.important23,
                R.id.important24, R.id.important25, R.id.important26, R.id.important27,
                R.id.important28, R.id.important29, R.id.important30, R.id.important31,
                R.id.important32, R.id.important33, R.id.important34, R.id.important35,
                R.id.important36, R.id.important37
        };
        
        int[] infoArray = new int[] {
                R.id.info0, R.id.info1, R.id.info2, R.id.info3,
                R.id.info4, R.id.info5, R.id.info6, R.id.info7,
                R.id.info8, R.id.info9, R.id.info10, R.id.info11,
                R.id.info12, R.id.info13, R.id.info14, R.id.info15,
                R.id.info16, R.id.info17, R.id.info18, R.id.info19,
                R.id.info20, R.id.info21, R.id.info22, R.id.info23,
                R.id.info24, R.id.info25, R.id.info26, R.id.info27,
                R.id.info28, R.id.info29, R.id.info30, R.id.info31,
                R.id.info32, R.id.info33, R.id.info34, R.id.info35,
                R.id.info36, R.id.info37
        };

        int[] iconArray = new int[] {
                R.id.icon0, R.id.icon1, R.id.icon2, R.id.icon3,
                R.id.icon4, R.id.icon5, R.id.icon6, R.id.icon7,
                R.id.icon8, R.id.icon9, R.id.icon10, R.id.icon11,
                R.id.icon12, R.id.icon13, R.id.icon14, R.id.icon15,
                R.id.icon16, R.id.icon17, R.id.icon18, R.id.icon19,
                R.id.icon20, R.id.icon21, R.id.icon22, R.id.icon23,
                R.id.icon24, R.id.icon25, R.id.icon26, R.id.icon27,
                R.id.icon28, R.id.icon29, R.id.icon30, R.id.icon31,
                R.id.icon32, R.id.icon33, R.id.icon34, R.id.icon35,
                R.id.icon36, R.id.icon37
        };

        // Get the JSON from OpenWeatherMap
        StringRequest stringRequest2 = new StringRequest(Request.Method.GET, url2, response -> {
            try {
                JSONObject raw = new JSONObject(response);
                JSONObject city1 = new JSONObject(raw.getString("city"));
                String offsetString = city1.getString("timezone");
                Long offset = Long.parseLong(offsetString);

                JSONArray array = new JSONArray(raw.getString("list"));

                // Parse that JSON and change the data for every 3 hours
                // this every 37 times
                for(int i = 0; i <= 37; i++) {
                    JSONObject a0 = new JSONObject(array.getString(i));
                    String a0date = a0.getString("dt");
                    Long a0utc = Long.parseLong(a0date);
                    TextView clock0 = b.findViewById(clockArray[i]);
                    clock0.setText(CustomCalendar.calculateClockTimeFromUTC(a0utc+offset));
                    TextView date0 = b.findViewById(dateArray[i]);
                    date0.setText(CustomCalendar.calculateDateFromUTC(i*3));
                    JSONObject a0temp = new JSONObject(a0.getString("main"));
                    JSONArray a0weather = new JSONArray(a0.getString("weather"));
                    JSONObject a0w = new JSONObject(a0weather.getString(0));
                    TextView important0 = b.findViewById(importantArray[i]);
                    String temp = a0temp.getString("temp") + " °C, " +
                                  a0w.getString("description");
                    important0.setText(temp);
                    JSONObject a0wind = new JSONObject(a0.getString("wind"));
                    JSONObject a0clouds = new JSONObject(a0.getString("clouds"));
                    TextView info0 = b.findViewById(infoArray[i]);
                    String string = a0wind.getString("speed")+ " m/s, clouds: " +
                                    a0clouds.getString("all")+"%";
                    info0.setText(string);
                    ImageView icon0 = b.findViewById(iconArray[i]);
                    String abc0 = a0w.getString("icon");
                    Functionality.setIcon(abc0,icon0);

                    String d0 = a0w.getString("description");
                    String id0 = a0w.getString("id");
                }
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }, Throwable::printStackTrace);
            // Set strings, initial

// Add the request to the RequestQueue.
        queue.add(stringRequest);
        queue.add(stringRequest2);

    }
}

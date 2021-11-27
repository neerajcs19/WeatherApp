package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.weatherapp.data.Global;
import com.example.weatherapp.functionality.Functionality;
import com.example.weatherapp.utility.FetchService;
import com.google.android.gms.location.LocationServices;


/**
 * Main class of this app.a
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Functionality that happens when the app is created
     *
     * @param savedInstanceState saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        LocationServices.getFusedLocationProviderClient(this);
    }

    /**
     * Defines user interface functionality, such as the two buttons and the initial search
     */
    @Override
    protected void onStart() {
        super.onStart();
        Global.a = this;

        final ScrollView scroll2 = this.findViewById(R.id.scroll2);
        Button button4 = this.findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scroll2.scrollTo(200,0);
            }
        });

        Button button5 = this.findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scroll2.scrollTo(200,450);
            }
        });

        AppCompatActivity a = this;
        final EditText editText = this.findViewById(R.id.currentLocationTextField);
        editText.setText("Mathura");
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(i == EditorInfo.IME_ACTION_DONE) {
                    Functionality.fetch(a,editText.getText().toString());
                }
                return false;
            }
        });

        Button button = this.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Functionality.fetch(a,editText.getText().toString());
                InputMethodManager inputMethodManager =
                        (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(view.getApplicationWindowToken(),0);
            }
        });
    }

    /**
     * Funcitonality when the app resumes
     */
    @Override
    protected void onResume() {
        super.onResume();
        Global.a = this;
        String city = "Hanover";

        // IMPORTANT!!!
        Functionality.fetch(this,city);
        Intent serviceIntent = new Intent(this, FetchService.class);
        startService(serviceIntent);
    }
}
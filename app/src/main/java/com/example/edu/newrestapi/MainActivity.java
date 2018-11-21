package com.example.edu.newrestapi;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    String weather;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Button btnSeoul = findViewById(R.id.btnSeoul);
//        btnSeoul.setOnClickListener(this);
        Button btnLondon = findViewById(R.id.btnLondon);
        btnLondon.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        TextView textViewResult = findViewById(R.id.textViewResult);
        OpenWeatherAPITask task = new OpenWeatherAPITask();
        try {
            weather = task.execute("London").get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        textViewResult.setText(weather);


    }
    class OpenWeatherAPITask extends AsyncTask<String, Void, String>{
        String weatherreturn;
        @Override
        protected String doInBackground(String... params) {
            String id = params[0];
            String urlString = "http://api.openweathermap.org/data/2.5/weather"+"?q="+id+ "&appid=13a6b9ddf0f31e46b33f160c1b8862b5";
            try {
                URL url = new URL(urlString);
                HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
                InputStream in = urlConnection.getInputStream();
                byte[] buffer = new byte[1000];
                in.read(buffer);
                weatherreturn = new String(buffer) ;

           }
           catch (MalformedURLException e) {
                e.printStackTrace();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return weatherreturn;
        }
    }
}

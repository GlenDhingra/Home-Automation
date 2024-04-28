package com.example.homeautomationjava;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {



    LinearLayout climateComponent, bedRoom2Component, kitchenComponent, livingRoomComponent, garageComponent, gardenComponent;
    ImageView climateImageView, bedRoom2ImageView, kitchenImageView, livingRoomImageView, garageImageView, gardenImageView;
    TextView climateTextView, bedRoom2TextView, kitchenTextView, livingRoomTextView, garageTextView, textViewRaindrop, gardenTextView;

    String rainDropString = "No Data yet!";

    Bundle bundle = new Bundle();

    String activityChosen="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);



        bedRoom2Component = (LinearLayout) findViewById(R.id.bedRoom2);
        bedRoom2TextView = (TextView) bedRoom2Component.findViewById(R.id.deviceName);
        bedRoom2TextView.setText("BedRoom");
        bedRoom2ImageView = (ImageView) bedRoom2Component.findViewById(R.id.deviceIcon);
        bedRoom2ImageView.setImageResource(R.drawable.hotel);


        kitchenComponent = (LinearLayout) findViewById(R.id.kitchen);
        kitchenTextView = (TextView) kitchenComponent.findViewById(R.id.deviceName);
        kitchenTextView.setText("Kitchen");
        kitchenImageView = (ImageView) kitchenComponent.findViewById(R.id.deviceIcon);
        kitchenImageView.setImageResource(R.drawable.kitchen);


        livingRoomComponent = (LinearLayout) findViewById(R.id.livingRoom);
        livingRoomTextView = (TextView) livingRoomComponent.findViewById(R.id.deviceName);
        livingRoomTextView.setText("Living Room");
        livingRoomImageView = (ImageView) livingRoomComponent.findViewById(R.id.deviceIcon);
        livingRoomImageView.setImageResource(R.drawable.livingroom);


        garageComponent = (LinearLayout) findViewById(R.id.garage);
        garageTextView = (TextView) garageComponent.findViewById(R.id.deviceName);
        garageTextView.setText("Garage");
        garageImageView = (ImageView) garageComponent.findViewById(R.id.deviceIcon);
        garageImageView.setImageResource(R.drawable.garage);

        gardenComponent = (LinearLayout) findViewById(R.id.garden);
        gardenTextView = (TextView) gardenComponent.findViewById(R.id.deviceName);
        gardenTextView.setText("Garden");
        gardenImageView = (ImageView) gardenComponent.findViewById(R.id.deviceIcon);
        gardenImageView.setImageResource(R.drawable.garden);

        textViewRaindrop = (TextView) findViewById(R.id.textViewRaindrop);
        textViewRaindrop.setText("");

        climateComponent = (LinearLayout) findViewById(R.id.climate);
        climateTextView = (TextView) climateComponent.findViewById(R.id.deviceName);
        climateImageView = (ImageView) climateComponent.findViewById(R.id.deviceIcon);
        climateImageView.setImageResource(R.drawable.sun);

        fetchTestData();


        bedRoom2Component.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, Bedroom2.class);
                activityChosen = "bedroom";
                bundle.putString("activity", activityChosen);
                i.putExtras(bundle);
                startActivity(i);
            }
        });

        livingRoomComponent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, LivingRoom.class);
                activityChosen = "livingRoom";
                bundle.putString("activity", activityChosen);
                i.putExtras(bundle);
                startActivity(i);
            }
        });

        kitchenComponent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, Kitchen.class);
                activityChosen = "kitchen";
                bundle.putString("activity", activityChosen);
                i.putExtras(bundle);
                startActivity(i);
            }
        });

        garageComponent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, Garage.class);
                activityChosen = "garage";
                bundle.putString("activity", activityChosen);
                i.putExtras(bundle);
                startActivity(i);
            }
        });


        gardenComponent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, Garden.class);
                activityChosen = "garden";
                bundle.putString("activity", activityChosen);
                i.putExtras(bundle);
                startActivity(i);
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    public void fetchTestData() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("http://192.168.76.5/DHTValues");
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    try {
                        InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                        StringBuilder result = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            result.append(line);
                        }
                        rainDropString = result.toString();
                        climateTextView.setText(rainDropString);
                    } finally {
                        urlConnection.disconnect();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }


    //new SendHttpRequestTask().execute();
    private class SendHttpRequestTask extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Void... voids) {
            try {
                URL url = new URL("http://192.168.240.5/ledOn");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                // Optionally, you can set request properties or handle the response here
                int responseCode = connection.getResponseCode();
                return responseCode == HttpURLConnection.HTTP_OK;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        @Override
        protected void onPostExecute(Boolean success) {
            super.onPostExecute(success);
            if (success) {
                // Request succeeded
                Toast.makeText(MainActivity.this, "LED Turned On", Toast.LENGTH_SHORT).show();
            } else {
                // Request failed
                Toast.makeText(MainActivity.this, "Failed to Turn On LED", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

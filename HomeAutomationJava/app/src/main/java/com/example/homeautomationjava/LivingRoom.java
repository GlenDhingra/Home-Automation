package com.example.homeautomationjava;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class LivingRoom extends AppCompatActivity {


    LinearLayout fanComponent, windowComponent, redComponent, greenComponent, blueComponent, whiteComponent;
    Switch fanSwitch, windowSwitch, redSwitch, greenSwitch, blueSwitch, whiteSwitch;
    TextView fanTextView, windowTextView, redTextView, greenTextView, blueTextView, whiteTextView;
    ImageView fanImageView, windowImageView, redImageView, blueImageView, greenImageView, whiteImageView;

    String serverComponent;
    Toast fanToast;
    String activity = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_living_room);


        Bundle bundle = getIntent().getExtras();
        activity = bundle.getString("activity");


        fanComponent = (LinearLayout) findViewById(R.id.fanControl);
        windowComponent = (LinearLayout) findViewById(R.id.windowControl);
        redComponent = (LinearLayout) findViewById(R.id.redControl);
        greenComponent = (LinearLayout) findViewById(R.id.greenControl);
        blueComponent = (LinearLayout) findViewById(R.id.blueControl);
        whiteComponent = (LinearLayout) findViewById(R.id.whiteControl);

        fanTextView = (TextView) fanComponent.findViewById(R.id.deviceName);
        fanTextView.setText("Fan");

        windowTextView = (TextView) windowComponent.findViewById(R.id.deviceName);
        windowTextView.setText("Window");

        redTextView = (TextView) redComponent.findViewById(R.id.deviceName);
        redTextView.setText("Red Light");

        greenTextView = (TextView) greenComponent.findViewById(R.id.deviceName);
        greenTextView.setText("Violet Light");

        blueTextView = (TextView) blueComponent.findViewById(R.id.deviceName);
        blueTextView.setText("Blue Light");

        whiteTextView = (TextView) whiteComponent.findViewById(R.id.deviceName);
        whiteTextView.setText("White Light");

        fanImageView = (ImageView) fanComponent.findViewById(R.id.deviceIcon);
        fanImageView.setImageResource(R.drawable.fanicon);

        windowImageView = (ImageView) windowComponent.findViewById(R.id.deviceIcon);
        windowImageView.setImageResource(R.drawable.window);

        redImageView = (ImageView) redComponent.findViewById(R.id.deviceIcon);
        redImageView.setImageResource(R.drawable.light);

        greenImageView = (ImageView) greenComponent.findViewById(R.id.deviceIcon);
        greenImageView.setImageResource(R.drawable.light);

        blueImageView = (ImageView) blueComponent.findViewById(R.id.deviceIcon);
        blueImageView.setImageResource(R.drawable.light);

        whiteImageView = (ImageView) whiteComponent.findViewById(R.id.deviceIcon);
        whiteImageView.setImageResource(R.drawable.light);


        fanSwitch = (Switch) fanComponent.findViewById(R.id.deviceSwitch);
        fanSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fanSwitch.isChecked()) {
                    fanToast = Toast.makeText(getApplicationContext(), "Fan Checked!!!", Toast.LENGTH_SHORT);
                    serverComponent = "fanOn";
                    new LivingRoom.SendHttpRequestTask().execute();
                } else {
                    fanToast = Toast.makeText(getApplicationContext(), "Fan Unchecked!!!", Toast.LENGTH_SHORT);
                    serverComponent = "fanOff";
                    new LivingRoom.SendHttpRequestTask().execute();
                }
                fanToast.show();
            }
        });

        windowSwitch = (Switch) windowComponent.findViewById(R.id.deviceSwitch);
        windowSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (windowSwitch.isChecked()) {
                    Toast.makeText(getApplicationContext(), "Window Checked!!!", Toast.LENGTH_SHORT).show();
                    serverComponent = "windowOpen";
                    new LivingRoom.SendHttpRequestTask().execute();
                } else {
                    Toast.makeText(getApplicationContext(), "Window Unchecked!!!", Toast.LENGTH_SHORT).show();
                    serverComponent = "windowClosed";
                    new LivingRoom.SendHttpRequestTask().execute();
                }
            }
        });

        redSwitch = (Switch) redComponent.findViewById(R.id.deviceSwitch);
        redSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (redSwitch.isChecked()) {
                    Toast.makeText(getApplicationContext(), "Red Checked!!!", Toast.LENGTH_SHORT).show();
                    serverComponent = "redLightOn";
                    new LivingRoom.SendHttpRequestTask().execute();
                } else {
                    Toast.makeText(getApplicationContext(), "Red Unchecked!!!", Toast.LENGTH_SHORT).show();
                    serverComponent = "redLightOff";
                    new LivingRoom.SendHttpRequestTask().execute();
                }
            }
        });

        greenSwitch = (Switch) greenComponent.findViewById(R.id.deviceSwitch);
        greenSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (greenSwitch.isChecked()) {
                    Toast.makeText(getApplicationContext(), "Green Checked!!!", Toast.LENGTH_SHORT).show();
                    serverComponent = "greenLightOn";
                    new LivingRoom.SendHttpRequestTask().execute();
                } else {
                    Toast.makeText(getApplicationContext(), "Green Unchecked!!!", Toast.LENGTH_SHORT).show();
                    serverComponent = "greenLightOff";
                    new LivingRoom.SendHttpRequestTask().execute();
                }
            }
        });

        blueSwitch = (Switch) blueComponent.findViewById(R.id.deviceSwitch);
        blueSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (greenSwitch.isChecked()) {
                    Toast.makeText(getApplicationContext(), "Blue Checked!!!", Toast.LENGTH_SHORT).show();
                    serverComponent = "blueLightOn";
                    new LivingRoom.SendHttpRequestTask().execute();
                } else {
                    Toast.makeText(getApplicationContext(), "Blue Unchecked!!!", Toast.LENGTH_SHORT).show();
                    serverComponent = "blueLightOff";
                    new LivingRoom.SendHttpRequestTask().execute();
                }
            }
        });

        whiteSwitch = (Switch) whiteComponent.findViewById(R.id.deviceSwitch);
        whiteSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (whiteSwitch.isChecked()) {
                    Toast.makeText(getApplicationContext(), "White Checked!!!", Toast.LENGTH_SHORT).show();
                    serverComponent = "whiteLightOn";
                    new LivingRoom.SendHttpRequestTask().execute();
                } else {
                    serverComponent = "whiteLightOff";
                    new LivingRoom.SendHttpRequestTask().execute();
                }
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private class SendHttpRequestTask extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Void... voids) {
            try {
                URL url = new URL("http://192.168.76.5/" + activity + "/" + serverComponent);
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
                Toast.makeText(LivingRoom.this, "LED Turned On", Toast.LENGTH_SHORT).show();
            } else {
                // Request failed
                Toast.makeText(LivingRoom.this, "Failed to Turn On LED", Toast.LENGTH_SHORT).show();
            }

            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        }
    }
}
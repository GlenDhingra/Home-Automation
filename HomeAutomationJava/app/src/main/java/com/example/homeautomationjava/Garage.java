package com.example.homeautomationjava;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.CompoundButton;
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

public class Garage extends AppCompatActivity {

    LinearLayout doorComponent;
    Switch doorSwitch;
    TextView doorTextView;
    ImageView doorImageView;

    String activity="";
    String serverComponent="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_garage);

        Bundle bundle = getIntent().getExtras();
        activity = bundle.getString("activity");

        doorComponent = (LinearLayout) findViewById(R.id.doorControl);

        doorTextView = (TextView) doorComponent.findViewById(R.id.deviceName);
        doorTextView.setText("Door");

        doorImageView = (ImageView) doorComponent.findViewById(R.id.deviceIcon);
        doorImageView.setImageResource(R.drawable.garagedoor);

        doorSwitch = (Switch) doorComponent.findViewById(R.id.deviceSwitch);

        doorSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Toast.makeText(getApplicationContext(),"Door Checked!!!",Toast.LENGTH_SHORT).show();
                    serverComponent = "doorOpen";
                    new Garage.SendHttpRequestTask().execute();
                }else{
                    Toast.makeText(getApplicationContext(),"Door Unchecked!!!",Toast.LENGTH_SHORT).show();
                    serverComponent = "doorClose";
                    new Garage.SendHttpRequestTask().execute();
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
                URL url = new URL("http://192.168.76.5/"+ activity + "/" +serverComponent);
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
                Toast.makeText(Garage.this, "LED Turned On", Toast.LENGTH_SHORT).show();
            } else {
                // Request failed
                Toast.makeText(Garage.this, "Failed to Turn On LED", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
package com.example.homeautomationjava;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
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

public class Bedroom2 extends AppCompatActivity {

    LinearLayout fanComponent, windowComponent, whiteComponent;
    Switch fanSwitch, windowSwitch, whiteSwitch;
    TextView fanTextView, windowTextView,  whiteTextView;
    ImageView fanImageView, windowImageView, whiteImageView;

    String serverComponent;
    Toast fanToast;
    String activity="";

    //Get the bundle

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bedroom2);

        Bundle bundle = getIntent().getExtras();
        activity = bundle.getString("activity");

        Toast.makeText(Bedroom2.this, activity, Toast.LENGTH_SHORT).show();

        fanComponent = (LinearLayout) findViewById(R.id.fanControl);
        windowComponent = (LinearLayout) findViewById(R.id.windowControl);
        whiteComponent = (LinearLayout) findViewById(R.id.whiteControl);

        fanTextView = (TextView) fanComponent.findViewById(R.id.deviceName);
        fanTextView.setText("Fan");

        windowTextView = (TextView) windowComponent.findViewById(R.id.deviceName);
        windowTextView.setText("Window");

        whiteTextView = (TextView) whiteComponent.findViewById(R.id.deviceName);
        whiteTextView.setText("White Light");

        fanImageView = (ImageView) fanComponent.findViewById(R.id.deviceIcon);
        fanImageView.setImageResource(R.drawable.fanicon);

        windowImageView = (ImageView) windowComponent.findViewById(R.id.deviceIcon);
        windowImageView.setImageResource(R.drawable.window);

        whiteImageView = (ImageView) whiteComponent.findViewById(R.id.deviceIcon);
        whiteImageView.setImageResource(R.drawable.light);


        fanSwitch = (Switch) fanComponent.findViewById(R.id.deviceSwitch);
        fanSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fanSwitch.isChecked()){
                    fanToast=Toast.makeText(getApplicationContext(),"Fan Checked!!!",Toast.LENGTH_SHORT);
                    serverComponent = "fanOn";
                    new SendHttpRequestTask().execute();
                } else{
                    fanToast=Toast.makeText(getApplicationContext(),"Fan Unchecked!!!",Toast.LENGTH_SHORT);
                    serverComponent = "fanOff";
                    new SendHttpRequestTask().execute();
                }
                fanToast.show();
            }
        });

        windowSwitch = (Switch) windowComponent.findViewById(R.id.deviceSwitch);
        windowSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( windowSwitch.isChecked() ){
                    Toast.makeText(getApplicationContext(),"Window Checked!!!",Toast.LENGTH_SHORT).show();
                    serverComponent = "windowOpen";
                    new SendHttpRequestTask().execute();
                }else{
                    Toast.makeText(getApplicationContext(),"Window Unchecked!!!",Toast.LENGTH_SHORT).show();
                    serverComponent = "windowClose";
                    new SendHttpRequestTask().execute();
                }
            }
        });

        whiteSwitch = (Switch) whiteComponent.findViewById(R.id.deviceSwitch);
        whiteSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( whiteSwitch.isChecked() ){
                    Toast.makeText(getApplicationContext(),"White Checked!!!",Toast.LENGTH_SHORT).show();
                    serverComponent = "whiteLightOn";
                    new SendHttpRequestTask().execute();
                }else{
                    serverComponent = "whiteLightOff";
                    new SendHttpRequestTask().execute();
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
                Toast.makeText(Bedroom2.this, "LED Turned On", Toast.LENGTH_SHORT).show();
            } else {
                // Request failed
                Toast.makeText(Bedroom2.this, "Failed to Turn On LED", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
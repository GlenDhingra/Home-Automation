package com.example.homeautomationjava;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
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

public class Kitchen extends AppCompatActivity {


    LinearLayout whiteComponent;
    Switch  whiteSwitch;
    TextView whiteTextView;
    ImageView whiteImageView;

    String activity="";
    String serverComponent="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_kitchen);


        Bundle bundle = getIntent().getExtras();
        activity = bundle.getString("activity");

        whiteComponent = (LinearLayout) findViewById(R.id.whiteControl);

        whiteTextView = (TextView) whiteComponent.findViewById(R.id.deviceName);
        whiteTextView.setText("White Text");

        whiteImageView = (ImageView) whiteComponent.findViewById(R.id.deviceIcon);
        whiteImageView.setImageResource(R.drawable.fanicon);

        whiteSwitch = (Switch) whiteComponent.findViewById(R.id.deviceSwitch);

        whiteSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Toast.makeText(getApplicationContext(),"White Checked!!!",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(),"White Unchecked!!!",Toast.LENGTH_SHORT).show();
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
                URL url = new URL("http://192.168.240.5/"+ activity + "/" +serverComponent);
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
                Toast.makeText(Kitchen.this, "LED Turned On", Toast.LENGTH_SHORT).show();
            } else {
                // Request failed
                Toast.makeText(Kitchen.this, "Failed to Turn On LED", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
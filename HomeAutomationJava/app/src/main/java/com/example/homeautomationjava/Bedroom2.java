package com.example.homeautomationjava;

import android.graphics.Color;
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

public class Bedroom2 extends AppCompatActivity {

    LinearLayout fanComponent, windowComponent, rgbComponent, whiteComponent;
    Switch fanSwitch, windowSwitch, rgbSwitch, whiteSwitch;
    TextView fanTextView, windowTextView, rgbTextView, whiteTextView;
    ImageView fanImageView, windowImageView, rgbImageView, whiteImageView;

    Toast fanToast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bedroom2);


        fanComponent = (LinearLayout) findViewById(R.id.fanControl);
        windowComponent = (LinearLayout) findViewById(R.id.windowControl);
        rgbComponent = (LinearLayout) findViewById(R.id.rgbControl);
        whiteComponent = (LinearLayout) findViewById(R.id.whiteControl);

        fanTextView = (TextView) fanComponent.findViewById(R.id.deviceName);
        fanTextView.setText("Fan");

        windowTextView = (TextView) windowComponent.findViewById(R.id.deviceName);
        windowTextView.setText("Window");

        rgbTextView = (TextView) rgbComponent.findViewById(R.id.deviceName);
        rgbTextView.setText("RGB Light");

        whiteTextView = (TextView) whiteComponent.findViewById(R.id.deviceName);
        whiteTextView.setText("White Text");

        fanImageView = (ImageView) fanComponent.findViewById(R.id.deviceIcon);
        fanImageView.setImageResource(R.drawable.fanicon);

        windowImageView = (ImageView) windowComponent.findViewById(R.id.deviceIcon);
        windowImageView.setImageResource(R.drawable.fanicon);

        rgbImageView = (ImageView) rgbComponent.findViewById(R.id.deviceIcon);
        rgbImageView.setImageResource(R.drawable.fanicon);

        whiteImageView = (ImageView) whiteComponent.findViewById(R.id.deviceIcon);
        whiteImageView.setImageResource(R.drawable.fanicon);


        fanSwitch = (Switch) fanComponent.findViewById(R.id.deviceSwitch);
        fanSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fanSwitch.isChecked()){
                    fanToast=Toast.makeText(getApplicationContext(),"Fan Checked!!!",Toast.LENGTH_SHORT);
                } else{
                    fanToast=Toast.makeText(getApplicationContext(),"Fan Unchecked!!!",Toast.LENGTH_SHORT);
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
                }else{
                    Toast.makeText(getApplicationContext(),"Window Unchecked!!!",Toast.LENGTH_SHORT).show();
                }
            }
        });

        rgbSwitch = (Switch) rgbComponent.findViewById(R.id.deviceSwitch);
        rgbSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( rgbSwitch.isChecked() ) {
                    Toast.makeText(getApplicationContext(),"RGB Checked!!!",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(),"RGB Unchecked!!!",Toast.LENGTH_SHORT).show();
                }
            }
        });

        whiteSwitch = (Switch) whiteComponent.findViewById(R.id.deviceSwitch);
        whiteSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( whiteSwitch.isChecked() ){
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
}
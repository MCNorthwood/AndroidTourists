package com.example.bg51az.comcet325bg51az;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity
{
    TextView about;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        about = (TextView)findViewById(R.id.showAbout);

        String info = "This app was created by Matthew Northwood \n©2017";

        about.setText(info);
    }
}

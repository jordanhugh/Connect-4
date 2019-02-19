package com.example.connect_4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button button0, button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_01);

        button0 = (Button) findViewById(R.id.playButton);
        button1 = (Button) findViewById(R.id.settingsButton);

        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //PLAY BUTTON
                //LINK TO NEXT PAGE
                openPGActivity();
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //LINK TO APP INFO ?
            }
        });
    }


    public void openPGActivity() {
       // Intent intent01 = new Intent(this, preGame01.class);
        //startActivity(intent01);
        startActivity(new Intent(MainActivity.this, preGame01.class));
    }


}
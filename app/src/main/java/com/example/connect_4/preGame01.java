package com.example.connect_4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.connect_4.R;

public class preGame01 extends AppCompatActivity{
    Button buttonSubmit;

    EditText p1;
    EditText p2;
    //Will these strings be accessible from other files?
    public static String name01,name02;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pre_game01);

        p1 = (EditText) findViewById(R.id.editText2);
        p2 = (EditText) findViewById(R.id.editText3);

        buttonSubmit = (Button) findViewById(R.id.buttonSubmit);
        buttonSubmit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Store the input names in string variables
                name01 = p1.getText().toString();
                name02 = p2.getText().toString();
                //Link this to next page
                openSecondActivity();

            }
        });
    }
    public void openSecondActivity(){
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }
}
package com.example.scantowtrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        setContentView(R.layout.activity_main);
    }

    public void loginDetails(View view) {

        Intent intent = new Intent(this, LoginPage.class);
        startActivity(intent);

    }

    public void vehicleStatusChecker(View view) {
        Intent intent = new Intent(this, checkVehicle.class);
        startActivity(intent);
    }
}
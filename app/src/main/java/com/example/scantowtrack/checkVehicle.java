package com.example.scantowtrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class checkVehicle extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_vehicle);
    }

    public void checkForTow(View view) {
        Intent intent = new Intent(this, statusOfTowing.class);
        startActivity(intent);
    }
}
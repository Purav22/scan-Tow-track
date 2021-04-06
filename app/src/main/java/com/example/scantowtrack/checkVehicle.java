package com.example.scantowtrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class checkVehicle extends AppCompatActivity {

    EditText num;
    String n;
    Button check;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        setContentView(R.layout.activity_check_vehicle);
        num = findViewById(R.id.vehicleNumber);
        check = findViewById(R.id.checkForTowing);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String noPlate = num.getText().toString().trim();
                noPlate = noPlate.toUpperCase();

                if(noPlate.length() != 0) {

                        Intent intent = new Intent(checkVehicle.this, statusOfTowing.class);
                        intent.putExtra("numberPlate", noPlate);
                        startActivity(intent);

                }else {
                    Toast.makeText(getApplicationContext(), "Please Enter Number", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}
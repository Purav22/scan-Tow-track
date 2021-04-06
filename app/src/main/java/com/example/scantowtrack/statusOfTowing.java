package com.example.scantowtrack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class statusOfTowing extends AppCompatActivity {
    TextView name,veh,fine,ownerName;
    ProgressBar prg;
    DatabaseReference reff;
    String No;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        setContentView(R.layout.activity_status_of_towing);

        name =(TextView)findViewById(R.id.owname);
        veh = (TextView)findViewById(R.id.vehno);
        fine = (TextView)findViewById(R.id.fine);
        ownerName = (TextView)findViewById(R.id.ownerName);
        prg = (ProgressBar)findViewById(R.id.ProgressBar);
        No = getIntent().getStringExtra("numberPlate");
        //Toast.makeText(getApplicationContext(),No,Toast.LENGTH_SHORT).show();
        prg.setVisibility(View.VISIBLE);
        onStart();
    }
    @Override
    public void onStart() {
        super.onStart();
        reff = FirebaseDatabase.getInstance().getReference().child(No);


        reff.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue() != null){
                    String fn = snapshot.child("Fine").getValue().toString();
                    String oname = snapshot.child("Name").getValue().toString();

                    String vn = snapshot.child("vehno").getValue().toString();
                    // Toast.makeText(getApplicationContext(),oname + "   " + fn + "   " + vn ,Toast.LENGTH_SHORT).show();
                    String n = "Name:     " + oname;
                    String f = "Fine:     " +fn;
                    String v = "Vehicle Number:    " + vn;
                    ownerName.setText(oname);
                    name.setText(n);
                    fine.setText(f);
                    veh.setText(v);
                    prg.setVisibility(View.INVISIBLE);

                }else{
                    prg.setVisibility(View.INVISIBLE);
                    new AlertDialog.Builder(statusOfTowing.this)
                            .setTitle("Oops!!!!")
                            .setMessage(No +" is invalid number plate")
                            .setCancelable(false)
                            .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(statusOfTowing.this, checkVehicle.class);
                                    startActivity(intent);

                                    finish();
                                }
                            }).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),"Not found", Toast.LENGTH_LONG).show();
            }
        });

    }
}
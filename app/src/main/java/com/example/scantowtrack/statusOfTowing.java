package com.example.scantowtrack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class statusOfTowing extends AppCompatActivity {
    TextView name,veh,fine,ownerName;
    DatabaseReference reff;
    String No;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_of_towing);

        name =(TextView)findViewById(R.id.owname);
        veh = (TextView)findViewById(R.id.vehno);
        fine = (TextView)findViewById(R.id.fine);
        ownerName = (TextView)findViewById(R.id.ownerName);
        No = getIntent().getStringExtra("numberPlate");
        //Toast.makeText(getApplicationContext(),No,Toast.LENGTH_SHORT).show();
        onStart();
    }
    @Override
    public void onStart() {
        super.onStart();
        reff = FirebaseDatabase.getInstance().getReference().child(No);
        reff.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
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
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),"Not found", Toast.LENGTH_LONG).show();
            }
        });

    }
}
package com.example.scantowtrack;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.FirebaseError;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.io.IOException;

public class previewBeforeTowing extends AppCompatActivity {
    private String currentPhotoPath;
    FirebaseAuth mAuth;
    DatabaseReference reff;
    EditText numerPlate;
    ProgressBar pb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        setContentView(R.layout.activity_preview_before_towing);
        mAuth = FirebaseAuth.getInstance();

        ImageButton btn = findViewById(R.id.imageButton);
        numerPlate = (EditText)findViewById(R.id.number);
        pb = (ProgressBar) findViewById(R.id.ProgressBar);
        }



    public void openCamera(View view) {
        String fileName = "photo";
        File storageDirectory = getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        try {
            File imageFile = File.createTempFile(fileName, ".PNG", storageDirectory);

            currentPhotoPath = imageFile.getAbsolutePath();

            Uri imageUri = FileProvider.getUriForFile(previewBeforeTowing.this,
                    "com.example.scantowtrack.fileprovider",imageFile);
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);

            startActivityForResult(intent, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK){
            Bitmap bitmap = BitmapFactory.decodeFile(currentPhotoPath);

            ImageView img = findViewById(R.id.numberPlate);

            img.setImageBitmap(bitmap);
        }
    }
    public void updateDb(View view){

        reff.child("Fine").setValue("500");
        pb.setVisibility(View.INVISIBLE);
        Intent i = new Intent(previewBeforeTowing.this, splashAfterScan.class);
        startActivity(i);

    }
    public void conform(View view) {
        pb.setVisibility(View.VISIBLE);
        String numberOFNP = numerPlate.getText().toString().trim();
        numberOFNP = numberOFNP.toUpperCase();
        if(numberOFNP.length() == 0){
            pb.setVisibility(View.INVISIBLE);
            Toast.makeText(getApplicationContext(), "Enter NumberPlate first!!!!", Toast.LENGTH_SHORT).show();
            return;
        }
        reff = FirebaseDatabase.getInstance().getReference().child(numberOFNP);

        reff.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue() != null){
                    updateDb(view);
                    numerPlate.setText("");
                }else{
                    pb.setVisibility(View.INVISIBLE);
                    Toast.makeText(getApplicationContext(),"No data found", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                pb.setVisibility(View.INVISIBLE);
                Toast.makeText(getApplicationContext(),"Not found", Toast.LENGTH_LONG).show();
            }
        });

    }

    public void signOut(View view) {
        mAuth.signOut();
        Intent intent = new Intent(previewBeforeTowing.this, LoginPage.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
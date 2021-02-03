package com.example.scantowtrack;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

import java.io.File;
import java.io.IOException;

public class previewBeforeTowing extends AppCompatActivity {
    private String currentPhotoPath;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_before_towing);
        mAuth = FirebaseAuth.getInstance();


    }



    public void openCamera(View view) {
        String fileName = "photo";
        File storageDirectory = getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        try {
            File imageFile = File.createTempFile(fileName, ".jpeg", storageDirectory);

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

    public void conform(View view) {
    }

    public void signOut(View view) {
        mAuth.signOut();
        Intent intent = new Intent(previewBeforeTowing.this, LoginPage.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
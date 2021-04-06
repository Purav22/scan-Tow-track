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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class takePicture extends AppCompatActivity {
    private String currentPhotoPath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        setContentView(R.layout.activity_take_picture);
    }

    public void openCamera(View view) {
//        String fileName = "photo";
//        File storageDirectory = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
//
//        try {
//            File imageFile = File.createTempFile(fileName, ".jpeg", storageDirectory);
//
//            currentPhotoPath = imageFile.getAbsolutePath();
//
//            Uri imageUri = FileProvider.getUriForFile(takePicture.this,
//                    "com.example.scantowtrack.fileprovider",imageFile);
//            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//            intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
//
//            startActivityForResult(intent, 1);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        Intent intent = new Intent(this, previewBeforeTowing.class);
        startActivity(intent);
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode == 1 && resultCode == RESULT_OK){
//            Bitmap bitmap = BitmapFactory.decodeFile(currentPhotoPath);
//            ImageView img = findViewById(R.id.img);
//            img.setImageBitmap(bitmap);
//
//
////            Intent intent = new Intent(this, previewBeforeTowing.class);
////            startActivity(intent);
//
//        }
//
//        Intent intent = new Intent(this, previewBeforeTowing.class);
//        startActivity(intent);
//    }
}
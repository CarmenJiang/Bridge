package com.example.carmen.name_app;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.net.URI;

public class AddNewPerson extends AppCompatActivity {
 ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_person);

        final Button pictureFromCamera = findViewById(R.id.addPictureFromCamera);
        final Button pictureFromGallery = findViewById(R.id.addPictureFromGallery);
        imageView = findViewById(R.id.showImage);
        pictureFromGallery.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                startActivityForResult(intent.createChooser(intent, "Select File"),0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode, data);
        //Bitmap bitmap = (Bitmap) data.getExtras().get("data");
        //imageView.setImageBitmap(bitmap);
        Uri imageUri = data.getData();
        imageView.setImageURI(imageUri);
        }
}

package com.example.carmen.name_app;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.net.URI;

public class AddNewPerson extends AppCompatActivity {
    ImageView imageView;
    private Uri imageUri;
    EditText editText;
    Bitmap bitmap;
    saveFileHandler sfh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_person);
        sfh = new saveFileHandler(getApplicationContext());
        final Button pictureFromCamera = findViewById(R.id.addPictureFromCamera);
        final Button pictureFromGallery = findViewById(R.id.addPictureFromGallery);
        final Button saveButton = findViewById(R.id.saveButton);
        imageView = findViewById(R.id.showImage);
        editText = findViewById(R.id.addName);

        pictureFromGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                startActivityForResult(intent.createChooser(intent, "Select File"), 0);
            }
        });

        pictureFromCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
                File photo = new File(dir, "Pic.jpg");
                imageUri = FileProvider.getUriForFile(getApplicationContext(),
                        "com.example.android.fileprovider",
                        photo);
                intent.putExtra(MediaStore.EXTRA_OUTPUT,
                        imageUri);
                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                startActivityForResult(intent, 1);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = editText.getText().toString();
                bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                if (bitmap != null && name != "" && name != null) {
                    String filename = name.toLowerCase().replace(' ', '_');

                    sfh.writeToPeople(name, filename);
                    sfh.saveImage(filename, bitmap);
                }
                PersonMap.map.put(name, bitmap);
                startActivity(new Intent(AddNewPerson.this, getParent().getClass()));
                finish();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK && requestCode == 0) {
            super.onActivityResult(requestCode, resultCode, data);
            //Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            //imageView.setImageBitmap(bitmap);
            Uri imageUri = data.getData();
            imageView.setImageURI(imageUri);
        }
        if (resultCode == RESULT_OK && requestCode == 1) {
            getContentResolver().notifyChange(imageUri, null);
            ContentResolver cr = getContentResolver();
            Bitmap bitmap;
            try {
                bitmap = android.provider.MediaStore.Images.Media
                        .getBitmap(cr, imageUri);

                imageView.setImageBitmap(bitmap);
                Toast.makeText(this, imageUri.toString(),
                        Toast.LENGTH_LONG).show();

            } catch (Exception e) {
                Toast.makeText(this, "Failed to load", Toast.LENGTH_SHORT)
                        .show();

            }


        }
    }
}
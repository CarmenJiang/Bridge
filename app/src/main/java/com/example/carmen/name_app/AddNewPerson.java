package com.example.carmen.name_app;

import android.content.ContentValues;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URI;

public class AddNewPerson extends AppCompatActivity {
    ImageView imageView;
    EditText editText;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_person);

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

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = editText.getText().toString();
                bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
                if (bitmap != null && name != "" && name != null) {
                    String filename = name.toLowerCase().replace(' ', '_');

                    try {



                        FileInputStream fileInputStream = openFileInput("people");
                        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                        String line;
                        String prevNames = "";
                        while ((line = bufferedReader.readLine()) != null) {
                            prevNames += (line + "\n");
                        }
                        Log.i("qwerty", prevNames);

                        FileOutputStream fOut = openFileOutput("people", Context.MODE_PRIVATE);
                        Log.i("qwerty", prevNames);
                            fOut.write(prevNames.getBytes());
                            fOut.write((name + "+" + filename + "\n").getBytes());
                        fOut.close();

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    ContextWrapper wrapper = new ContextWrapper(getApplicationContext());

                    // Initializing a new file
                    // The bellow line return a directory in internal storage

                    File file = wrapper.getDir("Images", MODE_PRIVATE);

                    // Create a file to save the image
                    file = new File(file, filename + ".jpg");

                    if (!file.exists()) {

                        Log.i("file Doesn't exist", file.getPath());
                        try {

                            OutputStream stream = null;

                            stream = new FileOutputStream(file);

                            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);

                            stream.flush();
                            stream.close();

                        } catch (IOException e) // Catch the exception
                        {
                            e.printStackTrace();
                        }
                    }
                }


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
    }
}

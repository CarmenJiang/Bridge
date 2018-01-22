package com.example.carmen.name_app;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        saveImages();

        if (!fileExist("people")) {

            try {
                FileOutputStream fOut = openFileOutput("people", Context.MODE_PRIVATE);

                final String[] info = getResources().getStringArray(R.array.people);

                for (String str : info)
                    fOut.write((str + "\n").getBytes());
                fOut.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        ImageButton settingsbtn = (ImageButton) findViewById(R.id.settingsButton);
        if (settingsbtn != null)
            settingsbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, SettingsActivity.class));
                }
            });

        Button listbtn = (Button) findViewById(R.id.listButton);
        if (listbtn != null)
            listbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, ListActivity.class));
                }
            });

        Button galleryButton = (Button) findViewById(R.id.galleryButton);

        if (galleryButton != null)
            galleryButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, ListGalleryActivity.class));
                }
            });

        Button learningModeButton = (Button) findViewById(R.id.learningButton);

        if (learningModeButton != null)
            learningModeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, LearningModeActivity.class));
                }
            });


    }

    private boolean fileExist(String fname) {
        File file = getBaseContext().getFileStreamPath(fname);
        return file.exists();
    }


    private void saveImages() {

        final String[] info = getResources().getStringArray(R.array.people);

        for (String str : info) {

            // Get the image from drawable resource as drawable object

            String imageName = str.split("\\+")[1];

            int id = getResources().getIdentifier(imageName, "drawable", getPackageName());


            Drawable drawable = (Drawable) getResources().getDrawable(id);

            // Get the bitmap from drawable object
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();


            ContextWrapper wrapper = new ContextWrapper(getApplicationContext());


            // Initializing a new file
            // The bellow line return a directory in internal storage

            File file = wrapper.getDir("Images", MODE_PRIVATE);


            // Create a file to save the image
            file = new File(file, imageName + ".jpg");

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


}

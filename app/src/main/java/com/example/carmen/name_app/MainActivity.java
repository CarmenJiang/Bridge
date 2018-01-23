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

        saveFileHandler sfh = new saveFileHandler(getApplicationContext());

        sfh.setupImages();
        sfh.setupPeople();





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

}

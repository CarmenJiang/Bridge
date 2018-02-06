package com.example.carmen.name_app;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {
StoreOwner so;
ImageView imageView;
    public Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.OwnerImage);
        SharedPreferences sharedPref = getSharedPreferences("OwnerSettings", Context.MODE_PRIVATE);
        String name = sharedPref.getString("owner", "Not found");
        Log.i("qwerty", name);

        if(name.equals("Not found")) {
            so = new StoreOwner(MainActivity.this);
            so.setCancelable(false);
            so.setCanceledOnTouchOutside(false);
            so.show();
        }

        saveFileHandler sfh = new saveFileHandler(getApplicationContext());


        sfh.setupPeople();
        PersonMap.setup(sfh);





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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        so.callBack(requestCode,resultCode,data);


    }

}

package com.example.carmen.name_app;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private Camera mCamera = null;
    TextView owner;
    ImageView showOwner;
    saveFileHandler sfh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        owner = findViewById(R.id.owner);
        sfh = new saveFileHandler(getApplicationContext());
        showOwner = findViewById(R.id.showOwner);
        SharedPreferences sharedPref = getSharedPreferences("OwnerSettings",Context.MODE_PRIVATE);
        String name = sharedPref.getString("owner", "Not found");

        owner.setText(name);

        Bitmap bm = sfh.getImage("owner");
        showOwner.setImageBitmap(bm);

        final Button button = findViewById(R.id.buttonHome);
        owner = findViewById(R.id.owner);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(SettingsActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

    }

}

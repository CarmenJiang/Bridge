package com.example.carmen.name_app;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URI;

public class showPersonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_person);
        TextView textView = (TextView) findViewById(R.id.personName);
        ImageView imageView = (ImageView) findViewById(R.id.personPicture);
        final int personNumber = getIntent().getIntExtra("PersonNumber", 0);
        ImageItem person = PersonMap.imageItems.get(personNumber);
        textView.setText(person.getTitle());
        imageView.setImageBitmap(person.getImage());

        final Button homeButton = findViewById(R.id.buttonHome);

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(showPersonActivity.this, MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });

        final ImageView deleteButton = findViewById(R.id.deleteButton);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PersonMap.imageItems.remove(personNumber);
            }
        });





       /* TextView textView = (TextView) findViewById(R.id.personName);*/


    }

}

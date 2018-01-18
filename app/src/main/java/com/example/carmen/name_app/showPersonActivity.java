package com.example.carmen.name_app;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.URI;

public class showPersonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_person);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView textView = (TextView) findViewById(R.id.personName);
        ImageView imageView = (ImageView) findViewById(R.id.personPicture);
        String[] info = getIntent().getStringExtra("name").split("\\+");
        textView.setText(info[0]);


        //set Picture
        Context context = imageView.getContext();
        int id = context.getResources().getIdentifier(info[1], "drawable", context.getPackageName());
        imageView.setImageResource(id);









       /* TextView textView = (TextView) findViewById(R.id.personName);*/
        setSupportActionBar(toolbar);


    }



}

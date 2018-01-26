package com.example.carmen.name_app;

import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class ListGalleryActivity extends AppCompatActivity {
    saveFileHandler sfh;
    final ArrayList<Bitmap> images = new ArrayList<Bitmap>();
    final ArrayList<String> names = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_gallery);
        final ImageView addPerson = findViewById(R.id.addPerson);

        addPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ListGalleryActivity.this, AddNewPerson.class);
                startActivity(i);
            }
        });


        Log.i("TAG","Setting up images");
        Iterator it = PersonMap.map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            names.add((String)pair.getKey());
            images.add((Bitmap)pair.getValue());
        }


        GridView gridview = (GridView) findViewById(R.id.gridView);
        gridview.setAdapter(new ImageAdapter(this, images.toArray(new Bitmap[images.size()])));
        final Button buttonHome = findViewById(R.id.buttonHome);


        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Intent intent = new Intent(ListGalleryActivity.this, showPersonActivity.class);
                intent.putExtra("name", names.get(position));
                startActivity(intent);
            }
        });

        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ListGalleryActivity.this, MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });
    }

//    @Override
//    protected void onResume(){
//        super.onResume();
//
//        Iterator it = PersonMap.map.entrySet().iterator();
//        while (it.hasNext()) {
//            Map.Entry pair = (Map.Entry)it.next();
//            names.add((String)pair.getKey());
//            images.add((Bitmap)pair.getValue());
//        }
//
//
//    }
}

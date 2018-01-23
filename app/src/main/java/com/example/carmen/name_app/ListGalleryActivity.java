package com.example.carmen.name_app;

import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ListGalleryActivity extends AppCompatActivity {
    saveFileHandler sfh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_gallery);
        sfh = new saveFileHandler(getApplicationContext());
        final ArrayList<String> info = sfh.getPeople();

        final String[] images = new String[info.size()];
        for (int i = 0; i < info.size(); i++) {
            images[i] = info.get(i).split("\\+")[1];
        }


        GridView gridview = (GridView) findViewById(R.id.gridView);
        gridview.setAdapter(new ImageAdapter(this, images, new ContextWrapper(getApplicationContext()).getDir("Images", MODE_PRIVATE).getPath()));
        final Button buttonHome = findViewById(R.id.buttonHome);


        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Intent intent = new Intent(ListGalleryActivity.this, showPersonActivity.class);
                intent.putExtra("personInfo", info.get(position));
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

}

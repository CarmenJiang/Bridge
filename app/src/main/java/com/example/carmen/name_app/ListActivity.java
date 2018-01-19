package com.example.carmen.name_app;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class ListActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.bensound_jazzyfrenchy);

            mediaPlayer.start();


        final Button button = findViewById(R.id.buttonHome);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ListActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        final String[] info = getResources().getStringArray(R.array.people);

        final String[] names = new String[info.length];
        for(int i = 0; i < info.length; i++){
            names[i] = info[i].split("\\+")[0];
        }

        final ListView listView = (ListView) findViewById(R.id.list);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, names);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ListActivity.this, showPersonActivity.class);
                intent.putExtra("personInfo", info[i]);
                startActivity(intent);

            }
        });


        }

    @Override
    protected void onPause(){
        super.onPause();
        mediaPlayer.stop();
        mediaPlayer.release();





    }

}

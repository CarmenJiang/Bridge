package com.example.carmen.name_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button buttonNameList = findViewById(R.id.nameList);
        final Button buttonLearningMode = findViewById(R.id.learningMode);
        final Button buttonListGallery = findViewById(R.id.pictureGallery);

//        buttonNameList.setOnClickListener(new View.OnClickListener(){
//            public void onClick(View v){
//                Intent i = new Intent(MainActivity.this, ListNamesActivity.class);
//                startActivity(i);
//            }
//                }
//        );

        buttonLearningMode.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent(MainActivity.this, LearningModeActivity.class);
                startActivity(i);
            }
        });

        buttonListGallery.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent(MainActivity.this, ListGalleryActivity.class);
                startActivity(i);
            }
        });


    }
}

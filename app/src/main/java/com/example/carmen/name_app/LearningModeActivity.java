package com.example.carmen.name_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class LearningModeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_mode);

final Button buttonQuestion = findViewById(R.id.startGame);
final Button buttonHome = findViewById(R.id.buttonHome);

buttonQuestion.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent i = new Intent(LearningModeActivity.this, LearningModeQuestion.class);
        startActivity(i);
    }
});

buttonHome.setOnClickListener(new View.OnClickListener(){
    public void onClick(View view){
        Intent i = new Intent(LearningModeActivity.this, MainActivity.class);
        startActivity(i);

    }
});


    }

}

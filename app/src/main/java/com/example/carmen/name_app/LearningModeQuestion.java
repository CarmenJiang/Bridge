package com.example.carmen.name_app;

import android.app.Activity;
import android.content.Context;
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
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class LearningModeQuestion extends AppCompatActivity {
 ArrayList<String> info;
int rand;
String correctName;
EditText enterName;
TextView sc;
MediaPlayer mediaPlayer;
    saveFileHandler sfh;
int score = 0;
int turns = 1;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
         sfh = new saveFileHandler(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_mode_question);
        sc = findViewById(R.id.score);
        sc.setText("Score: " + score);
        final Button buttonHome = findViewById(R.id.buttonHome);
        final Button next = findViewById(R.id.buttonNext);
        enterName = findViewById(R.id.enterName);
        info = sfh.getPeople();
         imageView = findViewById(R.id.randomPicture);
         nextPicture();

        buttonHome.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent i = new Intent(LearningModeQuestion.this, MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });

        next.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(correctName.equalsIgnoreCase(enterName.getText().toString())){
                    score++;
                    sc.setText("Score: " + score);

                }

                if(turns==10) {
                    Intent i = new Intent(LearningModeQuestion.this, ViewScoreActivity.class);
                    i.putExtra("score", score);
                    startActivity(i);
                }
                enterName.setText("");
                nextPicture();
                turns++;
hideKeyboard();
            }
        });

    }

    private void nextPicture(){
        Random rm = new Random();
        rand = rm.nextInt(info.size());
        correctName = info.get(rand).split("\\+")[0];
       Bitmap picture = sfh.getImage(info.get(rand).split("\\+")[1]);
        imageView.setImageBitmap(picture);
    }

    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = this.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(this);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.stop();
        mediaPlayer.release();


    }

    @Override
    protected void onResume(){
        super.onResume();
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.bensound_jazzyfrenchy);
        mediaPlayer.start();
    }
}

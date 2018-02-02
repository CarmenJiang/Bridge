package com.example.carmen.name_app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class LearningModeQuestion extends AppCompatActivity {
    ArrayList<String> info;
    int rand;
    String correctName;
    EditText enterName;
    TextView sc;
    MediaPlayer mediaPlayer;
    int score = 0;
    int turns = 1;
    ImageSwitcher imageSwitcher;
    Iterator<ImageItem> randomIterator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        randomIterator = null;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_mode_question);

        sc = findViewById(R.id.score);
        sc.setText("Score: " + score);

        final Button buttonHome = findViewById(R.id.buttonHome);
        final Button next = findViewById(R.id.buttonNext);

        enterName = findViewById(R.id.enterName);
        imageSwitcher = findViewById(R.id.randomPicture);



        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(getApplicationContext());
                imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
               imageView.setLayoutParams(new ImageSwitcher.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));
                return imageView;
            }
        });



        imageSwitcher.setImageDrawable(new BitmapDrawable(getResources(), nextPicture()));
        Animation in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in);
        Animation out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out);

        imageSwitcher.setInAnimation(in);
        imageSwitcher.setOutAnimation(out);

        buttonHome.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent(LearningModeQuestion.this, MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (correctName.equalsIgnoreCase(enterName.getText().toString())) {
                    score++;
                    sc.setText("Score: " + score);

                }

                if (turns == 10 || turns == PersonMap.imageItems.size()) {
                    Intent i = new Intent(LearningModeQuestion.this, ViewScoreActivity.class);
                    i.putExtra("score", score);
                    finish();
                    startActivity(i);
                }else{
                    imageSwitcher.setImageDrawable(new BitmapDrawable(getResources(), nextPicture()));
                }



                enterName.setText("");
                turns++;
                hideKeyboard();
            }
        });

    }

    private Bitmap nextPicture() {
        if(randomIterator ==null)
            setUpIterator();
        if(randomIterator.hasNext()) {
            ImageItem item = randomIterator.next();
            correctName = item.getTitle();
            return item.getImage();
        }
        return null;
    }

    private void setUpIterator() {
        ArrayList<ImageItem> newList = new ArrayList<ImageItem>(PersonMap.imageItems);
        long seed = System.nanoTime();
        Collections.shuffle(newList, new Random(seed));
        randomIterator = newList.iterator();

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
    protected void onResume() {
        turns = 0;
        super.onResume();
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.bensound_jazzyfrenchy);
        mediaPlayer.start();
    }
}

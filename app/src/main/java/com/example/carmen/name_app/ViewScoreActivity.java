package com.example.carmen.name_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ViewScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        int score = getIntent().getIntExtra("score", 0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_score);

        TextView scoreView = findViewById(R.id.scoreView);
        scoreView.setText(scoreView.getText() + " " + score);

        final Button buttonHome = findViewById(R.id.buttonHome);

        buttonHome.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent i = new Intent(ViewScoreActivity.this, MainActivity.class);
                startActivity(i);

            }
        });
    }
}

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
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView textView = (TextView) findViewById(R.id.personName);
        ImageView imageView = (ImageView) findViewById(R.id.personPicture);
        String[] info = getIntent().getStringExtra("personInfo").split("\\+");
        textView.setText(info[0]);

final Button button = findViewById(R.id.buttonHome);

button.setOnClickListener(new View.OnClickListener(){
    @Override
    public void onClick(View v) {
        Intent i = new Intent(showPersonActivity.this, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }
});


        //set Picture
//        Context context = imageView.getContext();
//        int id = context.getResources().getIdentifier(info[1], "drawable", context.getPackageName());
//        imageView.setImageResource(id);

        loadImageFromStorage( new ContextWrapper(getApplicationContext()).getDir("Images", MODE_PRIVATE).getPath(), info[1]);



       /* TextView textView = (TextView) findViewById(R.id.personName);*/
        setSupportActionBar(toolbar);


    }


    private void loadImageFromStorage(String path, String picName)
    {

        try {
            File f=new File( path, picName + ".jpg");
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
            ImageView img=(ImageView)findViewById(R.id.personPicture);
            img.setImageBitmap(b);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

    }

}

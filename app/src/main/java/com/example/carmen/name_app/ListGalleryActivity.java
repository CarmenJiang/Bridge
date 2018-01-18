package com.example.carmen.name_app;

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

public class ListGalleryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_gallery);

        final String[] aArr = getResources().getStringArray(R.array.people);

        final String[] bArr = new String[aArr.length];
        for(int i = 0; i < aArr.length; i++){
            bArr[i] = aArr[i].split("\\+")[1];
        }

        GridView gridview = (GridView) findViewById(R.id.gridView);
        gridview.setAdapter(new ImageAdapter(this,bArr));
        final Button buttonHome = findViewById(R.id.buttonHome);









        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Intent intent = new Intent(ListGalleryActivity.this, showPersonActivity.class);
                intent.putExtra("name", aArr[position]);
                startActivity(intent);
            }
        });

        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ListGalleryActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }

}

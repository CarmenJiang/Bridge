package com.example.carmen.name_app;


import android.app.Activity;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

import static android.app.Activity.RESULT_OK;

public class StoreOwner extends Dialog{

    public Activity activity;
    public Button save;
    ImageView imageView;
    EditText editText;
    Uri imageUri;
    Bitmap bitmap;
    saveFileHandler sfh;

 public StoreOwner(Activity activity){
     super(activity);
     this.activity = activity;
 }

 public void callBack(int requestCode, int resultCode, Intent data){
     Log.i("Kjem han seg hit?", "Ja");

     if (resultCode == RESULT_OK && requestCode == 0) {
         //Bitmap bitmap = (Bitmap) data.getExtras().get("data");
         //imageView.setImageBitmap(bitmap);
         Uri imageUri = data.getData();

         Log.i("ImageUri", imageUri.toString());
         imageView.setImageURI(imageUri);
     }
     if (resultCode == RESULT_OK && requestCode == 1) {
         activity.getContentResolver().notifyChange(imageUri, null);
         ContentResolver cr = activity.getContentResolver();
         Bitmap bitmap;
         try {
             bitmap = android.provider.MediaStore.Images.Media
                     .getBitmap(cr, imageUri);

             imageView.setImageBitmap(bitmap);

         } catch (Exception e) {
            e.printStackTrace();

         }


     }
 }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.owner);

        sfh = new saveFileHandler(activity.getApplicationContext());
        final Button pictureFromCamera = findViewById(R.id.OwnerCamera);
        final Button pictureFromGallery = findViewById(R.id.OwnerGallery);
        imageView = findViewById(R.id.OwnerImage);

        pictureFromGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                activity.startActivityForResult(intent.createChooser(intent, "Select File"), 0);
            }
        });

        pictureFromCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File dir = activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
                File photo = new File(dir, "Pic.jpg");
                imageUri = FileProvider.getUriForFile(activity.getApplicationContext(),
                        "com.example.android.fileprovider",
                        photo);
                intent.putExtra(MediaStore.EXTRA_OUTPUT,
                        imageUri);
                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                activity.startActivityForResult(intent, 1);
            }
        });


        save = (Button) findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPref = activity.getSharedPreferences("OwnerSettings", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("owner", ((TextView)(findViewById(R.id.owner))).getText().toString());
                editor.commit();
                dismiss();

                sfh.saveImage("owner", ((BitmapDrawable) imageView.getDrawable()).getBitmap());


            }
        });

    }




}

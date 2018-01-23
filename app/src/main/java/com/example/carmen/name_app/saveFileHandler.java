package com.example.carmen.name_app;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * Created by Eier on 23.01.2018.
 */

public class saveFileHandler {
    Context mContext;

    public saveFileHandler(Context context) {
        mContext = context;
    }

    public ArrayList<String> getPeople() {

        final ArrayList<String> info = new ArrayList<String>();

        try {
            FileInputStream fileInputStream = mContext.openFileInput("people");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                info.add(line);
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return info;
    }

    public Bitmap getImage(String imageName) {

        String path = new ContextWrapper(mContext).getDir("Images", mContext.MODE_PRIVATE).getPath();

        try {
            File f = new File(path, imageName + ".jpg");
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
            return b;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;

    }


    public void setupPeople(){
        if (!mContext.getFileStreamPath("people").exists()) {

            try {
                FileOutputStream fOut = mContext.openFileOutput("people", Context.MODE_PRIVATE);

                final String[] info = mContext.getResources().getStringArray(R.array.people);

                for (String str : info)
                    fOut.write((str + "\n").getBytes());
                fOut.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


    public void setupImages(){
        final String[] info = mContext.getResources().getStringArray(R.array.people);

        for (String str : info) {

            // Get the image from drawable resource as drawable object

            String imageName = str.split("\\+")[1];

            int id = mContext.getResources().getIdentifier(imageName, "drawable", mContext.getPackageName());


            Drawable drawable = (Drawable) mContext.getResources().getDrawable(id);

            // Get the bitmap from drawable object
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();


            ContextWrapper wrapper = new ContextWrapper(mContext);


            // Initializing a new file
            // The bellow line return a directory in internal storage

            File file = wrapper.getDir("Images", mContext.MODE_PRIVATE);


            // Create a file to save the image
            file = new File(file, imageName + ".jpg");

            if (!file.exists()) {

                Log.i("file Doesn't exist", file.getPath());
                try {

                    OutputStream stream = null;

                    stream = new FileOutputStream(file);

                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);

                    stream.flush();
                    stream.close();

                } catch (IOException e) // Catch the exception
                {
                    e.printStackTrace();
                }
            }
        }
    }

    public void saveImage(String imageName, Bitmap bitmap) {

        ContextWrapper wrapper = new ContextWrapper(mContext);

        // Initializing a new file
        // The bellow line return a directory in internal storage

        File file = wrapper.getDir("Images", mContext.MODE_PRIVATE);

        // Create a file to save the image
        file = new File(file, imageName + ".jpg");

        if (!file.exists()) {

            Log.i("file Doesn't exist", file.getPath());
            try {

                OutputStream stream = null;

                stream = new FileOutputStream(file);

                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);

                stream.flush();
                stream.close();

            } catch (IOException e) // Catch the exception
            {
                e.printStackTrace();
            }
        }

    }

    public void writeToPeople(String personName, String personImageName) {

        try {

            FileInputStream fileInputStream = mContext.openFileInput("people");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line;
            String prevNames = "";
            while ((line = bufferedReader.readLine()) != null) {
                prevNames += (line + "\n");
            }

            FileOutputStream fOut = mContext.openFileOutput("people", Context.MODE_PRIVATE);
            fOut.write(prevNames.getBytes());
            fOut.write((personName + "+" + personImageName + "\n").getBytes());
            fOut.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

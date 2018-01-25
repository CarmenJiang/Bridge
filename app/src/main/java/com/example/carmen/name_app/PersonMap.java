package com.example.carmen.name_app;

import android.graphics.Bitmap;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Eier on 25.01.2018.
 */

public class PersonMap {
    public static HashMap<String, Bitmap> map = new HashMap<String, Bitmap>();


    public static void setupMap(saveFileHandler sfh) {
        if (map.isEmpty()) {
        ArrayList<String> pList = sfh.getPeople();
            Log.i("TAG", "setup Happens");

            for (String s : pList) {
                String[] info = s.split("\\+");
                map.put(info[0], sfh.getImage(info[1]));
            }
        }
    }
}

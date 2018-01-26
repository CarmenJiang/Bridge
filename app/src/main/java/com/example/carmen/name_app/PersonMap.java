package com.example.carmen.name_app;

import android.graphics.Bitmap;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

/**
 * Created by Eier on 25.01.2018.
 */

public class PersonMap {
    public static TreeMap<String, Bitmap> map = new TreeMap<String, Bitmap>();
    private static saveFileHandler saveFileHandler;

    public static void setupMap(saveFileHandler sfh) {
        saveFileHandler = sfh;
        if (map.isEmpty()) {
        ArrayList<String> pList = sfh.getPeople();
            Log.i("PersonMap", "setup Happens");

            for (String s : pList) {
                String[] info = s.split("\\+");
                map.put(info[0], sfh.getImage(info[1]));
            }
        }
    }

    public static Bitmap getFullSizedPicture(String key){
        return saveFileHandler.getImage(key.toLowerCase().replace(' ', '_') + "_full_sized");
    }
}

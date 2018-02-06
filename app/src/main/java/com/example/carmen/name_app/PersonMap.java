package com.example.carmen.name_app;

import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

/**
 * Created by Eier on 25.01.2018.
 */

public class PersonMap {
    public static ArrayList<ImageItem> imageItems = new ArrayList<ImageItem>();
    private static saveFileHandler saveFileHandler;

    public static void setup(saveFileHandler sfh) {
        saveFileHandler = sfh;
        if (imageItems.isEmpty()) {
        ArrayList<String> pList = sfh.getPeople();
            Log.i("PersonMap", "setup happens");
            Log.i("pList.length", "" + pList.size());
            for (String s : pList) {
                String[] info = s.split("\\+");
                imageItems.add(new ImageItem(sfh.getImage(info[1]), info[0]));
            }
        }
    }

    public static void addImageItem(Bitmap bitmap, String title){
        imageItems.add(new ImageItem(bitmap, title));
    }

    public static String[] getNames(){
        String[] strArgs = new String[imageItems.size()];
        for(int i = 0; i < imageItems.size(); i++){
            strArgs[i] = imageItems.get(i).getTitle();
        }
        return strArgs;
    }

    public static Bitmap scaleDown(Bitmap realImage, float maxImageSize,
                            boolean filter) {
        float ratio = Math.min(
                (float) maxImageSize / realImage.getWidth(),
                (float) maxImageSize / realImage.getHeight());
        int width = Math.round((float) ratio * realImage.getWidth());
        int height = Math.round((float) ratio * realImage.getHeight());

        Bitmap newBitmap = Bitmap.createScaledBitmap(realImage, width,
                height, filter);
        return newBitmap;
    }

    public static Bitmap getFullSizedPicture(String key){
        return saveFileHandler.getImage(key.toLowerCase().replace(' ', '_') + "_full_sized");
    }
}

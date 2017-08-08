package com.example.image_selectro_lib.models;


import com.example.image_selectro_lib.SelectorSettings;

import java.util.ArrayList;
/**
 * Created by yang2 on 2017/8/8.
 */
public class ImageListContent {
    public static boolean bReachMaxNumber = false;

    public static final ArrayList<ImageItem> IMAGES = new ArrayList<ImageItem>();

    public static void clear()
    {
        IMAGES.clear();
    }
    public static void addItem(ImageItem item) {
        IMAGES.add(item);
    }

    public static final ArrayList<String> SELECTED_IMAGES = new ArrayList<>();

    public static boolean isImageSelected(String filename) {
        return SELECTED_IMAGES.contains(filename);
    }

    public static void toggleImageSelected(String filename) {
        if(SELECTED_IMAGES.contains(filename)) {
            SELECTED_IMAGES.remove(filename);
        } else {
            SELECTED_IMAGES.add(filename);
        }
    }

    public static final ImageItem cameraItem = new ImageItem("", SelectorSettings.CAMERA_ITEM_PATH, 0);
}

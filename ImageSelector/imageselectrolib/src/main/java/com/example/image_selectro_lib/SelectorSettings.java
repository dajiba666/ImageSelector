package com.example.image_selectro_lib;

import java.util.ArrayList;

/**
 * Created by zfdang on 2016-4-18.
 */
public class SelectorSettings {

    public static final String SELECTOR_MAX_IMAGE_NUMBER = "selector_max_image_number";
    public static int mMaxImageNumber = 9;


    public static final String SELECTOR_SHOW_CAMERA = "selector_show_camera";
    public static boolean isShowCamera = true;
    public static final String CAMERA_ITEM_PATH = "/CAMERA/CAMERA";


    public static final String SELECTOR_INITIAL_SELECTED_LIST = "selector_initial_selected_list";
    public static ArrayList<String> resultList = new ArrayList<>();


    public static final String SELECTOR_RESULTS = "selector_results";


    public static int mMinImageSize = 50000;
    public static final String SELECTOR_MIN_IMAGE_SIZE = "selector_min_image_size";
    public static  int REQUEST_CODE = 0x00001;

}

package com.example.image_selectro_lib;

import android.app.Activity;
import android.content.Intent;

import java.util.ArrayList;

import static com.example.image_selectro_lib.SelectorSettings.REQUEST_CODE;

/**
 * Created by yang2 on 2017/8/8.
 */

public class ImageConfig {
    private static Activity mActivity ;

    public ImageConfig setMax(int max){
        SelectorSettings.mMaxImageNumber = max;
        return this;
    }
    public static ImageConfig getInstance(Activity a){
        mActivity = a;
        return Holder.IMAGE_CONFIG;
    }
    public ImageConfig setMinImageSize(int size){
        SelectorSettings.mMinImageSize = size;
        return this;
    }

    public ImageConfig setCamera(boolean camera){
        SelectorSettings.isShowCamera = camera;
        return this;
    }
    //    public static final int REQUEST_CODE = 0x00001;
    public ImageConfig setCode(int code){
        REQUEST_CODE = code;
        return this;
    }

    public ImageConfig setSelectList(ArrayList<String> results){
        SelectorSettings.resultList = results;
        return this;
    }

    private static class Holder {
        public static final ImageConfig IMAGE_CONFIG = new ImageConfig();
    }

    public void action(){
        Intent intent = new Intent(mActivity,ImagesSelectorActivity.class);
        intent.putExtra(SelectorSettings.SELECTOR_MAX_IMAGE_NUMBER, SelectorSettings.mMaxImageNumber);
        intent.putExtra(SelectorSettings.SELECTOR_MIN_IMAGE_SIZE, SelectorSettings.mMinImageSize);
        intent.putExtra(SelectorSettings.SELECTOR_SHOW_CAMERA, SelectorSettings.isShowCamera);
        intent.putStringArrayListExtra(SelectorSettings.SELECTOR_INITIAL_SELECTED_LIST, SelectorSettings.resultList);
        mActivity.startActivityForResult(intent, REQUEST_CODE);
    }

}

package com.example.image_selectro_lib.utils;

/**
 * Created by yang2 on 2017/8/8.
 */
public class StringUtils {
    public static String getLastPathSegment(String content) {
        if(content == null || content.length() == 0){
            return "";
        }
        String[] segments = content.split("/");
        if(segments.length > 0) {
            return segments[segments.length - 1];
        }
        return "";
    }

}

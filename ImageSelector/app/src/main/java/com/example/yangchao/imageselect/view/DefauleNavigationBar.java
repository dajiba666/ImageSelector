package com.example.yangchao.imageselect.view;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.example.yangchao.imageselect.R;
import com.example.yangchao.imageselect.navigationbar.AbsNavigationBar;


/**
 * Created by yang2 on 2017/6/20.
 */

public class DefauleNavigationBar extends AbsNavigationBar<DefauleNavigationBar.Builder.DefauleNavigationParams> {
    public DefauleNavigationBar(Builder.DefauleNavigationParams params) {
        super(params);
    }

    @Override
    public int bindLayoutId() {
        return R.layout.support_simple_spinner_dropdown_item;
    }

    @Override
    public void applyView() {
        setText(R.id.title,getParams().mTitle);
        setRightText(R.id.tv_right_title,getParams().mRightTitle);
        setOnClickListener(R.id.tv_right_title,getParams().mRightlis);
        setOnClickListener(R.id.iv_lefticon,getParams().mBack);
    }



    public static class Builder extends AbsNavigationBar.Builder{

        DefauleNavigationParams P;

        public Builder(Context context, ViewGroup parent) {
            super(context,parent);
            P = new DefauleNavigationParams(context,parent);
        }
        public Builder(Context context){
            super(context);
            P = new DefauleNavigationParams(context);
        }
        @Override
        public DefauleNavigationBar builer() {
            DefauleNavigationBar defauleNavigationBar =  new DefauleNavigationBar(P);
            return defauleNavigationBar;
        }

        public DefauleNavigationBar.Builder setTitle(String title){

            P.mTitle = title;
            return this;
        }

        public DefauleNavigationBar.Builder setRightText(String title){
            P.mRightTitle = title;
            return this;
        }

        public DefauleNavigationBar.Builder setRightIcon(int rightRes){
            P.mRightIcon = rightRes;
            return this;
        }

        public DefauleNavigationBar.Builder setRightClickListener(View.OnClickListener rightlis){
            P.mRightlis = rightlis;
            return this;
        }

        public DefauleNavigationBar.Builder setBackClickListener(View.OnClickListener back){
            P.mBack = back;
            return this;
        }


        public static class DefauleNavigationParams extends AbsNavigationBar.Builder.AbsNavigationParams{

            public String mTitle;
            public String mRightTitle;
            public int mRightIcon;
            public View.OnClickListener mRightlis;
            public View.OnClickListener mBack = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((Activity)mContext).finish();
                }
            };

            public DefauleNavigationParams(Context context, ViewGroup parent) {
                super(context, parent);
            }

            public DefauleNavigationParams(Context context) {
                super(context);
            }

        }
    }
}

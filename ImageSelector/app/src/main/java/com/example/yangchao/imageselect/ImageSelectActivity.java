package com.example.yangchao.imageselect;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;

import com.example.yangchao.imageselect.view.DefauleNavigationBar;

import java.io.File;
import java.util.ArrayList;


/**
 * Created by 超 on 2017/7/18.
 */

public class ImageSelectActivity extends BaseActivity{
    //多选
    public static final  int MODE_MULTI = 0X00001;

    //单选
    public static final int MODE_SINGLE = 0X00002;

    //是否选择相机

    public static final String EXTRA_SHOW_CAMERA = "EXTRA_SHOW_CAMERA";


    //选择多少张图片key
    public static final String EXTRA_SELECT_COUNT = "EXTRA_SELECT_COUNT";

    //原始的图片路径的EXTRA_KEY
    public static final String EXTRA_DEFAULT_SELECTED_LIST ="EXTRA_DEFAULT_SELECTED_LIST";

    //选择模式
    public static final String EXTRA_SELECT_MODE = "EXTRA_SELECT_MODE";
    //图片列表key
    public static final String EXTRA_RESULT ="EXTRA_RESULT";
    private static final int LOADER_TYPE = 0x000022 ;


    //传递的参数
    private int mMode = MODE_MULTI;

    private int mMaxCount = 8;

    private boolean mShowCamera = true;

    private ArrayList<String> mResultList;

    private Button mCompleteSelectorBt;
    private RecyclerView imgSelect;

    private TextView mSelectNum;

    private TextView mTvPre;
    private ImageSelectAdapter imageSelectAdapter;
    private TextView tvOk;
    ArrayList<String> strings = new ArrayList<>();
    @Override
    protected void initEvent() {
        mTvPre.setOnClickListener(view -> {

        });

        tvOk.setOnClickListener(view -> {
                Intent intent = new Intent();
                intent.putStringArrayListExtra(EXTRA_RESULT,mResultList);
                setResult(RESULT_OK);
                finish();
        });
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        mShowCamera = intent.getBooleanExtra(EXTRA_SHOW_CAMERA,true);
        mResultList = intent.getStringArrayListExtra(EXTRA_DEFAULT_SELECTED_LIST);
        mMaxCount = intent.getIntExtra(EXTRA_SELECT_COUNT,9);
        mMode = intent.getIntExtra(EXTRA_SELECT_MODE,0X00001);
        if (mResultList == null){
            mResultList = new ArrayList<>();
        }

        initImageList();

        exchangeViewShow();
    }

    private void exchangeViewShow() {
        if (mResultList.size()>0){
            mTvPre.setTextColor(Color.BLUE);
        }else {
            mTvPre.setTextColor(Color.WHITE);
        }

        mSelectNum.setText(mResultList.size()+"/"+mMaxCount);



    }

    private void initImageList() {
        getLoaderManager().initLoader(LOADER_TYPE,null,callback);

    }
    private LoaderManager.LoaderCallbacks<Cursor> callback = new LoaderManager.LoaderCallbacks<Cursor>() {

        private final String[] IMAGE_PROJECTION = {
                MediaStore.Images.Media.DATA,
                MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.DATE_ADDED,
                MediaStore.Images.Media.MIME_TYPE,
                MediaStore.Images.Media.SIZE,
                MediaStore.Images.Media._ID


        };


        @Override
        public Loader<Cursor> onCreateLoader(int id, Bundle args) {
            CursorLoader cursorLoader = new CursorLoader(ImageSelectActivity.this
                    ,MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                    ,IMAGE_PROJECTION,IMAGE_PROJECTION[4]+">0 AND "
                    +IMAGE_PROJECTION[3] + "=? OR "+IMAGE_PROJECTION[3] +"=? ",new String[]{"image/jpeg","image/png"},
                    IMAGE_PROJECTION[2]+" DESC");


            return cursorLoader;
        }

        @Override
        public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
            if (data !=null &&data.getCount()>0){
                ArrayList<String> images = new ArrayList<>();
                if (mShowCamera ){
                    images.add("");
                }
                while (data.moveToNext()){
                    String path = data.getString(data.getColumnIndexOrThrow(IMAGE_PROJECTION[0]));
                    String name = data.getString(data.getColumnIndexOrThrow(IMAGE_PROJECTION[1]));
                    long time = data.getLong(data.getColumnIndexOrThrow(IMAGE_PROJECTION[2]));

                    if (!pathEist(path)){
                        continue;
                    }
                    ImageEntity image = new ImageEntity(path,name,time);

                    images.add(path);

                }

                showListData(images);
            }

        }

        @Override
        public void onLoaderReset(Loader<Cursor> loader) {

        }
    };

    private void showListData(ArrayList<String> images) {
        imageSelectAdapter = new ImageSelectAdapter(images,this,mResultList,mMaxCount);
        imageSelectAdapter.setImageSelectListener(num -> {
                mResultList = num;
                exchangeViewShow();
        });
        imgSelect.setLayoutManager(new GridLayoutManager(this,4));
        imgSelect.setAdapter(imageSelectAdapter);

    }

    private boolean pathEist(String path) {
        File file = new File(path);
        if (!file.exists()){
            return false;
        }
        return true;


    }

    @Override
    protected void initView() {
        DefauleNavigationBar defauleNavigationBar = new DefauleNavigationBar.Builder(this)
                .setTitle("所有图片")
                .builer();
        imgSelect = (RecyclerView) findViewById(R.id.rcv_img_select);
        mTvPre = (TextView) findViewById(R.id.tv_pre);
       mSelectNum = (TextView) findViewById(R.id.tv_num);
        tvOk = (TextView) findViewById(R.id.tv_ok);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_image_select;
    }

}

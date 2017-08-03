package com.example.yangchao.imageselect;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.yangchao.imageselect.aop.Permission;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn).setOnClickListener((v)->
                onClick()

        );
    }
    @Permission({READ_EXTERNAL_STORAGE,WRITE_EXTERNAL_STORAGE})
    private void onClick() {
        startActivity(new Intent(this,ImageSelectActivity.class));
    }
}

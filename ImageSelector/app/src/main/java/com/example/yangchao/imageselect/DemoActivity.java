package com.example.yangchao.imageselect;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.image_selectro_lib.ImageConfig;
import com.example.image_selectro_lib.SelectorSettings;

import java.util.ArrayList;

import static com.example.image_selectro_lib.SelectorSettings.REQUEST_CODE;

/**
 * Created by yang2 on 2017/8/8.
 */

public class DemoActivity extends AppCompatActivity {
    private ArrayList<String> mResults;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                select();
            }
        });
    }

    private void select() {
        ImageConfig.getInstance(this)
                .setCamera(true)
                .setMax(9)
                .setMinImageSize(100)
                .action();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_CODE) {
            if(resultCode == RESULT_OK) {
                mResults = data.getStringArrayListExtra(SelectorSettings.SELECTOR_RESULTS);
                assert mResults != null;

                // show results in textview
                StringBuilder sb = new StringBuilder();
                for(String result : mResults) {
                    sb.append(result).append("\n");
                }
                Toast.makeText(this,sb.toString(),Toast.LENGTH_SHORT).show();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}

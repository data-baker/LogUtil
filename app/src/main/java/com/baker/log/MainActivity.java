package com.baker.log;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.baker.log.util.LogUtil;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LogUtil.error("测试信息");

    }
}
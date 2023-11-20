package com.baker.log;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.baker.log.util.LogConstants;
import com.baker.log.util.LogUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LogConstants.getInstance().setSignatureString("  ");
        LogConstants.getInstance().setLogUrl(" ");
        LogUtil.openLogUtilStream(this);
        LogUtil.error("测试 配置");

    }
}
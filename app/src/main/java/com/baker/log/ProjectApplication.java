package com.baker.log;

import android.app.Application;

import com.baker.log.util.ExceptionHandlerUtil;
import com.baker.log.util.LogConstants;
import com.baker.log.util.LogUtil;

public class ProjectApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LogConstants.getInstance().setLogConfig("urlString","signatureString",false);
        LogUtil.openLogUtilStream(this);
        ExceptionHandlerUtil.getInstance().init(this);
    }
}

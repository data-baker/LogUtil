package com.baker.log.util;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class BakerLogUpload {
    private volatile static BakerLogUpload instance = null;

    private BakerLogUpload() {

    }

    public static BakerLogUpload getInstance() {
        if (null == instance) {
            synchronized (BakerLogUpload.class) {
                if (null == instance) {
                    instance = new BakerLogUpload();
                }
            }
        }
        return instance;
    }


    private BaseInfo baseInfo;
    private RequestJsonBody requestJsonBody;
    private Context mContext;
    private final MediaType contentType = MediaType.parse("application/json; charset=utf-8");


    private BaseInfo getBaseInfo() {
        if (baseInfo == null) {
            baseInfo = new BaseInfo(DeviceIdUtil.getManufacturer() + "-" + DeviceIdUtil.getModel(), LogConstants.getInstance().getAppVersion(), LogConstants.getInstance().getAppName(), Locale.getDefault().getLanguage(), DeviceIdUtil.getSDKVersionName() + "-" + DeviceIdUtil.getSDKVersionCode());
        }
        return baseInfo;
    }

    private RequestJsonBody getRequestJsonBody() {
        if (requestJsonBody == null) {
            requestJsonBody = new RequestJsonBody(getBaseInfo(), "info", LogConstants.getInstance().getBusinessType());
        }
        return requestJsonBody;
    }

    private String getAuthorizationString() {
        TreeMap<String, String> originParams = new TreeMap<>();
        originParams.put("level", getRequestJsonBody().getLevel());
        originParams.put("userid", getRequestJsonBody().getUserid());
        originParams.put("businessType", getRequestJsonBody().getBusinessType());
        originParams.put("time", getRequestJsonBody().getTime());
        originParams.put("systemVersion", getBaseInfo().getSystemVersion());
        originParams.put("appVersion", getBaseInfo().getAppVersion());
        originParams.put("appName", getBaseInfo().getAppName());
        originParams.put("language", getBaseInfo().getLanguage());
        originParams.put("appSystemVersion", getBaseInfo().getAppSystemVersion());
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, String> entry : originParams.entrySet()) {
            builder.append(entry.getKey());
            builder.append("_");
            builder.append(entry.getValue());
            builder.append("_");
        }
        String authorizationString = builder.toString();
        return HMACSHA256Utils
                .sha256_HMAC(authorizationString.substring(0, authorizationString.length() - 1),
                        LogConstants.getInstance().getSignatureString());

    }

    public void setContext(Context mContext) {
        this.mContext = mContext;
    }

    public void d(String contentString) {
        d(Arrays.asList(contentString));
    }

    public void d(List<String> logs) {
        upload(logs, "info");
    }


    public void e(String contentString) {
        e(Arrays.asList(contentString));
    }

    public void e(List<String> logs) {
        upload(logs, "error");
    }


    private void upload(List<String> logs, String level) {

        if (!LogConstants.getInstance().isUpload()) return;
        if (mContext == null) return;
        getRequestJsonBody().setContentList(logs);
        getRequestJsonBody().setTime(String.valueOf(System.currentTimeMillis()));
        getRequestJsonBody().setUserid(DeviceIdUtil.getDeviceId(mContext));
        getRequestJsonBody().setLevel(level);
        String paramJson = GsonUtils.toJson(getRequestJsonBody());

        RequestBody body = RequestBody.create(contentType, paramJson);
        Request request = new Request.Builder().addHeader("Authorization", getAuthorizationString()).url(LogConstants.getInstance().getLogUrl()).post(body).build();

        BakerOkHttpClient.getInstance().getHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

            }
        });
    }


}

package com.example.helper;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.webkit.WebSettings;

/**
 * Created by chinaso on 2015/12/15.
 */
public class MyWebSetting {
    private WebSettings webSetting;
    private Context context;
    public MyWebSetting(Context mycontext, WebSettings myWebSettings){
        this.webSetting=myWebSettings;
        this.context=mycontext;
       initMyWeb();
    }

    //初始化设置，即基本setting
    private void initMyWeb() {
        webSetting.setDomStorageEnabled(true);
        webSetting.setGeolocationEnabled(true);
        webSetting.setDatabaseEnabled(true);
        webSetting.setJavaScriptEnabled(true);
        webSetting.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);//设置缓存
        webSetting.setSavePassword(false);
    }

    //请求用户地理位置
    public void setDatabasePath() {
        String dir=context.getApplicationContext().getDir("webdatabase",Context.MODE_PRIVATE).getPath();
        webSetting.setGeolocationDatabasePath(dir);
        webSetting.setDatabasePath(dir);
    }

    // 设置缓存
    public void setSaveMode() {
        webSetting.setSaveFormData(true);
        webSetting.setLoadWithOverviewMode(true);

    }
    //设置Zoom模式
    public void setZoomMode() {
        webSetting.setBuiltInZoomControls(true);
        webSetting.setSupportZoom(true);
        webSetting.setDisplayZoomControls(false);
    }

    //设置广泛视角
    public void setUseWideViewPort(Boolean bl){
        webSetting.setUseWideViewPort(bl);
    }

    public void setAppCache(String string){
        webSetting.setAppCacheEnabled(true);
        webSetting.setAppCachePath(string);
    }

    public void setNewUserAgentString() {
        try {
            webSetting.setUserAgentString(versionName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String versionName() throws Exception {
        PackageManager manager = context.getPackageManager();
        PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
        return webSetting.getUserAgentString() + " ChinasoClient version:" + info.versionName;
    }
}

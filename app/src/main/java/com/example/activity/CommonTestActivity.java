package com.example.activity;


import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.androidtest.R;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;



public class CommonTestActivity extends Activity {

    Button btn;
    static String TAG="ly-s";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_test);
        init();
    }

    private void init() {
        btn = (Button) findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //launchAppDetail(CommonTestActivity.this, "com.chinaso.so", "com.qihoo.appstore");
                saveCrashInfoToFile(CommonTestActivity.this);
            }
        });
    }

    /**
     * 启动到应用商店app详情界面
     *
     * @param appPkg    目标App的包名
     * @param marketPkg 应用商店包名 ,如果为""则由系统弹出应用商店列表供用户选择,否则调转到目标市场的应用详情界面，某些应用商店可能会失败
     */
    public void launchAppDetail(Context context, String appPkg, String marketPkg) {
        try {
            if (TextUtils.isEmpty(appPkg)) return;
            Uri uri = Uri.parse("market://details?id=" + appPkg);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            if (!TextUtils.isEmpty(marketPkg)) {
                intent.setPackage(marketPkg);
            }
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            PackageManager packageManager = context.getPackageManager();
            List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, 0);

            if (activities.size() == 0) {
                intent.setPackage(null);
                activities = packageManager.queryIntentActivities(intent, 0);
                if (activities.size() > 0) {
                    startActivity(intent);
                } else {
                    Log.i("ly-s", "jump to browser");

                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveCrashInfoToFile(Context context) {
        // 这是在日志中为了标记出现在开始的是 Crash log，不要用 AppEnv.bAppdebug 的开关关掉


        FileOutputStream fos = null;
        try {
            File root=Environment.getExternalStorageDirectory();
            File dir = new File(root, "testdir");
            if (!dir.exists()) {
                dir.mkdir();
            }
            File test=new File(dir, "test");
            fos = new FileOutputStream(test);

            fos.write("test log".getBytes());
            fos.flush();

            Uri data = Uri. parse("file:///"+test.getPath());
            sendBroadcast( new  Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE , data));

        } catch (Exception e) {
            Log.e(TAG, "an error occured while writing report file...", e);// 故意保留的日志，不要放在debug标志里面
        } finally {
            try {
                fos.close();
            } catch (Exception e) {
                Log.e(TAG, "an error occured while writing report file-close...", e);// 故意保留的日志，不要放在debug标志里面
            }
        }

        Log.v(TAG, "Crash Log END");// 故意保留的日志，不要放在debug标志里面
    }

}
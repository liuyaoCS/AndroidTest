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
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.androidtest.R;

import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;

import static android.content.pm.PackageManager.MATCH_DEFAULT_ONLY;


public class CommonTestActivity extends Activity {

    Button btn,btn1;
    static String TAG="ly-s";

    public static final String SYS_EMUI = "HONOR"; //华为
    public static final String SYS_MIUI = "Xiaomi"; //小米
    public static final String SYS_FLYME = "Meizu"; //魅族
    public static final String SYS_VIVO = "vivo"; //vivo
    public static final String SYS_OPPO = "oppo"; //oppo
    public static final String SYS_SANXING = "samsung"; //三星

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_test);
        init();
    }

    private void init() {
        btn = (Button) findViewById(R.id.btn);
        btn1 = (Button) findViewById(R.id.btn1);

        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //launchAppDetail(CommonTestActivity.this, "com.chinaso.so", "com.qihoo.appstore");
                //saveCrashInfoToFile(CommonTestActivity.this);
                //tryStartSupportAppByIntent(CommonTestActivity.this);
                //tryStartSupportAppByScheme(CommonTestActivity.this,"youon://","com.shuishan.ridespot");
                openAutoStartSetting(CommonTestActivity.this);
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //startApp(CommonTestActivity.this);
                startAppTest(CommonTestActivity.this);
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
    public static boolean tryStartSupportAppByScheme(Context context, String url, String packagename) {

        Uri uri = Uri.parse(url);
        Intent intent = new Intent();//START_APP_ACTION
        intent.setData(uri);
        //intent.setPackage(packagename);
//        intent.addCategory(Intent.CATEGORY_APP_BROWSER);
//        intent.addCategory(Intent.CATEGORY_DEFAULT);

        long start = System.currentTimeMillis();
        PackageManager packageManager = context.getPackageManager();

//        ComponentName cn=intent.resolveActivity(packageManager);
//        if(cn==null){
//            Log.i("ly-s","cn null");
//            return false;
//        }

        List<ResolveInfo> infos=packageManager.queryIntentActivities(intent, 0);
        if (infos.size() > 0) {
            ResolveInfo info=infos.get(0);
            context.startActivity(intent);
            return true;
        } else {
            return false;
        }


    }
    public static boolean tryStartSupportAppByIntent(Context context) {


        Intent intent = new Intent("com.bluegogo.co");
        intent.setPackage("com.beastbike.bluegogo");

        intent.putExtra("method", "scan");
        intent.putExtra("channel", "360");
        intent.putExtra("data_url", "https%3A%2F%2Fwww.bluegogo.com%2Fqrcode.html%3Fno%3D123456789");


        context.startService(intent);

//        PackageManager packageManager = context.getPackageManager();
//
//        if (packageManager.queryIntentActivities(intent, 0).size() > 0) {
//            context.startActivity(intent);
//        } else if (packageManager.queryIntentServices(intent, 0).size() > 0) {
//            context.startService(intent);
//        } else if (packageManager.queryBroadcastReceivers(intent, 0).size() > 0) {
//            context.sendBroadcast(intent);
//        } else {
//            return false;
//        }
        return true;
    }
    public static void startApp(Context context){


        Uri uri = Uri.parse("QHScannerQidian://?from=360&ret=https%3A%2F%2Fwww.bluegogo.com%2Fqrcode.html%3Fno%3D11111111111");
        Intent intent = new Intent();//"com.common.safeqr.CALLUP"
        //intent.setData(uri);
        intent.setPackage("com.shuishan.ridespot");
        //intent.addCategory(Intent.CATEGORY_BROWSABLE);

        context.startActivity(intent);
    }
    public static void startAppTest(Context context){


        Uri uri = Uri.parse("QHScannerQidian://?from=360&ret=https%3A%2F%2Fwww.bluegogo.com%2Fqrcode.html%3Fno%3D11111111111");
        Intent intent = new Intent();//"com.common.safeqr.CALLUP"
        //intent.setData(uri);
        intent.setPackage("com.shuishan.ridespot");
        //intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.putExtra("serializable_key", BigInteger.valueOf(1));


        context.startActivity(intent);
    }
    public static void openAutoStartSetting(Context context) {
        String system = android.os.Build.BRAND;

        Intent intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ComponentName comp = null;
        if (system.equals(SYS_EMUI)) {//华为
            comp = ComponentName
                    .unflattenFromString("com.huawei.systemmanager/.optimize.process.ProtectActivity");
        } else if (system.equals(SYS_MIUI)) {//小米
            comp = ComponentName
                    .unflattenFromString("com.miui.securitycenter/com.miui.permcenter.autostart.AutoStartManagementActivity");
        } else if (system.contains(SYS_VIVO)) {//vivo
            comp = ComponentName
                    .unflattenFromString("com.iqoo.secure/.ui.phoneoptimize.AddWhiteListActivity");
        } else if (system.contains(SYS_OPPO)) {//oppo  未测试
            comp = ComponentName
                    .unflattenFromString("com.coloros.oppoguardelf/com.coloros.powermanager.fuelgaue.PowerUsageModelActivity");
        }else if (system.contains(SYS_FLYME)) {//魅族
            comp = ComponentName
                    .unflattenFromString("com.meizu.safe/.permission.SmartBGActivity");
        }else if (system.contains(SYS_SANXING)) {//三星 不可行
            comp = ComponentName
                    .unflattenFromString("com.samsung.android.sm/.ui.ram.RamActivity");
        }
        intent.setComponent(comp);

        try {
            context.startActivity(intent);
        } catch (Exception e) {//抛出异常就直接打开设置页面
            intent = new Intent(Settings.ACTION_SETTINGS);
            context.startActivity(intent);
        }
    }
}
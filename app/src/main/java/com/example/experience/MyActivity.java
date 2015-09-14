package com.example.experience;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import com.example.androidtest.R;

public class MyActivity extends Activity {

    View bt,bt_brower,bt_brower2,service_common,service_bind,service_unbind,service_intent,send,provider;
    String action="com.example.androidTest.TEST_ACTION";
    private static final String ACTION_BROADCAST ="com.example.androidTest.BROADCAST_ACTION" ;

    public ServiceConnection conn=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyServiceBind.MyBind ib=(MyServiceBind.MyBind)service;
            String s=ib.str;
            int ret=ib.fun(2,3);
            Log.i("ly","s= "+s+" ret= "+ret);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i("ly","unbind");
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        Log.i("ly", "oncreate");
        MyReceiver rec=new MyReceiver();
        IntentFilter filter=new IntentFilter(ACTION_BROADCAST);
        registerReceiver(rec,filter);

        bt=this.findViewById(R.id.bt);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(it);
            }
        });

        bt_brower=findViewById(R.id.bt_brower);
        bt_brower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri=Uri.parse("http://m.chinaso.com");
                Intent it=new Intent(Intent.ACTION_VIEW,uri);
                it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(it);
            }
        });

        bt_brower2=findViewById(R.id.bt_brower2);
        bt_brower2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Intent it=new Intent(MyActivity.this, CommonActivity.class);
//                it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(it);

                Intent it=new Intent(action);
                Uri uri=Uri.parse("http://www.baidu.com:80");
                it.setData(uri);
                startActivity(it);
            }
        });

        service_common=findViewById(R.id.service_common);
        service_common.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent service=new Intent();
                service.setClass(MyActivity.this,MyServiceCommon.class);
                startService(service);
            }
        });

        service_bind=findViewById(R.id.service_bind);
        service_bind.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Intent service=new Intent();
                service.setClass(MyActivity.this,MyServiceBind.class);
                bindService(service,conn,BIND_AUTO_CREATE);
            }
        });

        service_unbind=findViewById(R.id.service_unbind);
        service_unbind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindService(conn);
            }
        });

        service_intent=findViewById(R.id.service_intent);
        service_intent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent service=new Intent(MyActivity.this,MyIntentService.class);
                startService(service);
            }
        });

        send=findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cast=new Intent(ACTION_BROADCAST);
                sendBroadcast(cast);
            }
        });

        provider=findViewById(R.id.provider);
        provider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(MyActivity.this,ContentProviderActivity.class);
                startActivity(it);
            }
        });

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public Object onRetainNonConfigurationInstance() {
        return super.onRetainNonConfigurationInstance();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("ly", "onpause ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("ly", "onresume ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("ly", "onstop ");
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        Log.i("ly", "onSaveInstanceState");
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onRestoreInstanceState(savedInstanceState, persistentState);
        Log.i("ly", "onRestoreInstanceState");
    }
}

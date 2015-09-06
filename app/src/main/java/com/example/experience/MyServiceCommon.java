package com.example.experience;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by rmss on 2015/8/17.
 */
public class MyServiceCommon extends Service{
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("ly", "startcommand");
        String threadName=Thread.currentThread().getName();
        Log.i("ly", "threadname," + threadName);
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i("ly", "startcommand over");
        return super.onStartCommand(intent, flags, startId);
    }

    int i=0;
    @Override
    public void onCreate() {
        Log.i("ly", "create");



        new Thread(){
            @Override
            public void run() {
                while(i++!=20){
                    try {
                        Thread.currentThread().sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.i("ly", "i= "+i);
                }
                stopSelf();
            }
        }.start();
        super.onCreate();
    }

}

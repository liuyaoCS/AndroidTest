package com.example.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Created by rmss on 2015/8/18.
 */
public class MyIntentService extends IntentService{

    public MyIntentService(){
        super("com.example.service.MyIntentService");
        String threadName=Thread.currentThread().getName();
        Log.i("ly-s", "threadname=" + threadName);
    }
    @Override
    protected void onHandleIntent(Intent intent) {
        String threadName=Thread.currentThread().getName();
        Log.i("ly-s", "onHandleIntent," + threadName);
        try {
            Thread.sleep(11000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

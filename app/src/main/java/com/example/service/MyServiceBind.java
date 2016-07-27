package com.example.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by rmss on 2015/8/17.
 */
public class MyServiceBind extends Service{
    MyBind mBind=new MyBind();
    @Override
    public IBinder onBind(Intent intent) {
        Log.i("ly", "onbind");
        return mBind;
    }
    class MyBind extends Binder {
        public String str="hahha";
        public int fun(int a,int b){
            return a+b;
        }

    }
}

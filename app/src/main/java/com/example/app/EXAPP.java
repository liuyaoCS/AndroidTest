package com.example.app;

import android.app.Application;
import cn.jpush.android.api.JPushInterface;



public class EXAPP extends Application {
	//private static EXAPP soAPP;

//    public EXAPP() {
//    }
 
    @Override
    public void onCreate() {
    	super.onCreate();
    	//soAPP = this;
    	
    	//RequestManager.init(this);
    	
    	JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        
        //com.umeng.socialize.utils.Log.LOG = true;
    }
}

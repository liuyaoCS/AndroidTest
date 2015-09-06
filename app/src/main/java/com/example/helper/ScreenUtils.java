package com.example.helper;

import android.content.Context;

public class ScreenUtils {

	static long BASE_WIDTH_PIX=720;
	static long BASE_HEIGHT_PIX=1080;
	public static int widthAdapt(int width,int width_px){
		return (int) (width_px*width/BASE_WIDTH_PIX);
	}
	public static int dip2px(Context context, float dpValue) {  
        final float scale = context.getResources().getDisplayMetrics().density;  
        return (int) (dpValue * scale + 0.5f);  
    }  
    public static int px2dip(Context context, float pxValue) {  
        final float scale = context.getResources().getDisplayMetrics().density;  
        return (int) (pxValue / scale + 0.5f);  
    }  
}

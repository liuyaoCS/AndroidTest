package com.example.helper;

import android.content.Context;
import android.util.Log;

public class DisplayUtil {
	public static int Dp2Px(Context context, float dp) { 
	    final float scale = context.getResources().getDisplayMetrics().density; 
	    return (int) (dp * scale + 0.5f); 
	} 
	 
	public static int Px2Dp(Context context, float px) { 
	    final float scale = context.getResources().getDisplayMetrics().density; 
	    return (int) (px / scale + 0.5f); 
	} 
	public static void show(Context context){
		int width=context.getResources().getDisplayMetrics().widthPixels;
		int height=context.getResources().getDisplayMetrics().heightPixels;
		int dpi=context.getResources().getDisplayMetrics().densityDpi;
		Log.i("ly", "width= "+width+"height "+height+"dpi "+dpi);
	}
}

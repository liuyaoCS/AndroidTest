package com.example.experience;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;

import com.example.androidtest.R;

public class AdapterActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapter);

        Display display=getWindowManager().getDefaultDisplay();

        DisplayMetrics dm=new DisplayMetrics();
        display.getMetrics(dm);

        DisplayMetrics rdm=new DisplayMetrics();
        display.getRealMetrics(rdm);

        DisplayMetrics dms=getResources().getDisplayMetrics();

        Log.i("ly", display.getWidth() + " " + display.getHeight());
        Log.i("ly", dms.density+" "+dms.scaledDensity+" "+dms.densityDpi+" "+dms.heightPixels+" "+dms.widthPixels);
        Log.i("ly", dm.density+" "+dm.scaledDensity+" "+dm.densityDpi+" "+dm.heightPixels+" "+dm.widthPixels);
        Log.i("ly", rdm.density+" "+rdm.scaledDensity+" "+rdm.densityDpi+" "+rdm.heightPixels+" "+rdm.widthPixels);

        Log.i("ly",""+getStatusBarHeight());

    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

}

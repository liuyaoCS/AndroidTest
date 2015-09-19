package com.example.experience;

import android.content.Context;

import android.support.v4.view.GestureDetectorCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.Scroller;
import android.widget.TextView;

import com.example.androidtest.R;


/**
 * Created by rmss on 2015/9/18.
 */
public class DraggerView extends LinearLayout {

//    TextView scan,general,star;
//    LinearLayout dragger_container;
//    GestureDetectorCompat gd;
//    private Scroller mScroller;
    public DraggerView(Context context) {
        super(context);
        init(context);
    }

    public DraggerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
    private void init(Context context){
        LayoutInflater.from(context).inflate(R.layout.dragger_layout,this);
    }

    @Override
    public void computeScroll() {
        Log.i("ly","computeScroll");
        if(DraggerViewActivity.scroller.computeScrollOffset()){
            scrollTo(DraggerViewActivity.scroller.getCurrX(),0);
            Log.i("ly", "Scroll...now" + DraggerViewActivity.scroller.getCurrX());
            invalidate();
        }
    }
}

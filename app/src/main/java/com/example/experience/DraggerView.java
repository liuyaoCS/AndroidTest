package com.example.experience;

import android.content.Context;

import android.support.v4.view.GestureDetectorCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.androidtest.R;


/**
 * Created by rmss on 2015/9/18.
 */
public class DraggerView extends LinearLayout {

//    TextView scan,general,star;
//    LinearLayout dragger_container;
//    GestureDetectorCompat gd;
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
//        scan= (TextView) findViewById(R.id.cap_scan);
//        general= (TextView) findViewById(R.id.cap_general);
//        star= (TextView) findViewById(R.id.cap_star);
//        dragger_container= (LinearLayout) findViewById(R.id.dragger_container);
//        gd=new GestureDetectorCompat(context,new GestureDetector.SimpleOnGestureListener(){
//            @Override
//            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
//                Log.i("ly","onScroll-->"+distanceX);
//                dragger_container.scrollTo((int)distanceX,0);
//                return super.onScroll(e1, e2, distanceX, distanceY);
//            }
//
//            @Override
//            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
//                Log.i("ly","onFling-->"+velocityX);
//                return super.onFling(e1, e2, velocityX, velocityY);
//            }
//
//            @Override
//            public boolean onDown(MotionEvent e) {
//                Log.i("ly","onDown");
//                return true;
//            }
//        });
    }


}

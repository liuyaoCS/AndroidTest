package com.example.experience;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.GestureDetectorCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.androidtest.R;

public class DraggerViewActivity extends Activity {
    GestureDetectorCompat gd;
    TextView scan,general,star;
    RelativeLayout dragger_container;
    DraggerView draggerView;
    int scan_l,scan_r;
    int general_l,general_r;
    int star_l,star_r;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dragger_view);
        init(this);
        new Handler().post(new Runnable(){

            @Override
            public void run() {
                scan_l=scan.getLeft();
                scan_r=scan.getRight();

                general_l=general.getLeft();
                general_r=general.getRight();

                star_l=star.getLeft();
                star_r=star.getRight();

                Log.i("ly","measure-->"+scan_l+", "+star_r);
            }
        });
    }
    private void init(Context context){
       // LayoutInflater.from(context).inflate(R.layout.dragger_layout,this);
        scan= (TextView) findViewById(R.id.cap_scan);
        general= (TextView) findViewById(R.id.cap_general);
        star= (TextView) findViewById(R.id.cap_star);
        dragger_container= (RelativeLayout) findViewById(R.id.dragger_container);
        draggerView= (DraggerView) findViewById(R.id.draggerView);
        gd=new GestureDetectorCompat(context,new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                Log.i("ly", "onScroll-->" + distanceX);
                draggerView.scrollBy((int) distanceX, 0);
                return super.onScroll(e1, e2, distanceX, distanceY);
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                Log.i("ly","onFling-->"+velocityX);
                return super.onFling(e1, e2, velocityX, velocityY);
            }


        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gd.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
}

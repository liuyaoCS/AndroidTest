package com.example.activity.experience;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.GestureDetectorCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Scroller;
import android.widget.TextView;

import com.example.androidtest.R;

public class DraggerViewActivity extends Activity {

    TextView scan,general,star;
    DraggerView draggerView;
    private ViewGroup wrapper;
    int scan_l,scan_r;
    int general_l,general_r;
    int star_l,star_r;
    private  int MAX_DIS;

    GestureDetectorCompat gd;
    public static Scroller scroller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dragger_view);
        init(this);
        scroller=new Scroller(this);
        new Handler().post(new Runnable(){

            @Override
            public void run() {
                scan_l=scan.getLeft();
                scan_r=scan.getRight();
                Log.i("ly",""+scan.getHeight());

                general_l=general.getLeft();
                general_r=general.getRight();

                star_l=star.getLeft();
                star_r=star.getRight();

                MAX_DIS =scan.getWidth()+24*3;
                Log.i("ly","measure-->"+scan_l+", "+star_r);
            }
        });
        general.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                Log.i("ly","LayoutListener "+scan.getHeight());
                if(scan.getHeight()!=0){
                    general.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    //updateUI();
                }
            }
        });
    }
    private void init(Context context){

        scan= (TextView) findViewById(R.id.cap_scan);
        general= (TextView) findViewById(R.id.cap_general);
        star= (TextView) findViewById(R.id.cap_star);

        draggerView= (DraggerView) findViewById(R.id.draggerView);
        wrapper= (ViewGroup) findViewById(R.id.wrapper);

        gd=new GestureDetectorCompat(context,new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
               // Log.i("ly", "onScroll-->" + distanceX);
                if(distanceX>0){
                    if(draggerView.getScrollX()>=MAX_DIS){
                        draggerView.scrollTo(MAX_DIS,0);
                    }else{
                        draggerView.scrollBy((int) distanceX, 0);

                    }
                }else{
                    if(draggerView.getScrollX()<=-MAX_DIS){
                        draggerView.scrollTo(-MAX_DIS,0);
                    }else{
                        draggerView.scrollBy((int) distanceX, 0);
                    }
                }
                return super.onScroll(e1, e2, distanceX, distanceY);
            }


        });
    }

    @Override
      public boolean onTouchEvent(MotionEvent event) {
        gd.onTouchEvent(event);
        int action=event.getAction();
        switch(action){
            case MotionEvent.ACTION_UP:
                int scrollX=draggerView.getScrollX();
                if(scrollX==0 || Math.abs(scrollX)==MAX_DIS){
                    break;
                }

                if(scrollX>MAX_DIS/2){
                    scroller.startScroll(scrollX, 0, MAX_DIS - scrollX, 0, 500);
                }else if(scrollX>0){
                    scroller.startScroll(scrollX, 0, 0 - scrollX, 0, 500);
                }else if(scrollX>-MAX_DIS/2){
                    scroller.startScroll(scrollX,0,0- scrollX,0,500);
                }else if(scrollX>-MAX_DIS){
                    scroller.startScroll(scrollX,0,-MAX_DIS - scrollX,0,500);
                }

                wrapper.invalidate();
                break;
            default:
                break;
        }


        return super.onTouchEvent(event);
    }
}

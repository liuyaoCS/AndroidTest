package com.example.experience;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.GestureDetectorCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Scroller;
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
    private  int MAX_DIS;
    public static Scroller scroller;
    private ViewGroup wrapper;
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

                general_l=general.getLeft();
                general_r=general.getRight();

                star_l=star.getLeft();
                star_r=star.getRight();

                MAX_DIS =scan.getWidth()+24*3;
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
                //wrapper.invalidate();

                return super.onScroll(e1, e2, distanceX, distanceY);
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
               // Log.i("ly","onFling-->"+velocityX);
                return super.onFling(e1, e2, velocityX, velocityY);
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
                    scroller.startScroll(scrollX, 0, MAX_DIS - scrollX, 0, 1000);
                   // Log.i("ly", "begin left smooth 2 scroll-->"+(MAX_DIS - scrollX));
                }else if(scrollX>0){
                    scroller.startScroll(scrollX, 0, 0 - scrollX, 0, 1000);
                  //  Log.i("ly", "begin left smooth 1 scroll-->"+(0- scrollX));
                }else if(scrollX>-MAX_DIS/2){
                    //Log.i("ly", "begin right smooth 1 scroll-->"+(0- scrollX));
                    scroller.startScroll(scrollX,0,0- scrollX,0,1000);
                }else if(scrollX>-MAX_DIS){
                   // Log.i("ly", "begin right smooth 2 scroll-->"+(-MAX_DIS - scrollX));
                    scroller.startScroll(scrollX,0,-MAX_DIS - scrollX,0,1000);
                }
 //               Log.i("ly", " scrollX,scroller :"+scrollX+","+ scroller.getCurrX());
//                if(scrollX>0){
//                    scroller.startScroll(scrollX,0,0- scrollX,0,1000);
//                    Log.i("ly", "begin left smooth 1 scroll-->"+(0- scrollX));
//                }else {
//                    Log.i("ly", "begin right smooth 1 scroll-->" + (0 - scrollX));
//                    scroller.startScroll(0, 0, 0 - scrollX, 0, 1000);
//                }
                wrapper.invalidate();
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }
}

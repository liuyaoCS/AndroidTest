package com.example.activity.view;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.widget.ScrollerCompat;
import android.view.View;
import android.widget.Button;    
import android.view.View.OnClickListener;

public class ScrollerActivity extends Activity {      
    MyRelativeLayout mylayout; 
    Button btn1,btn2;
     private ScrollerCompat mScroller;    
    @Override    
    public void onCreate(Bundle savedInstanceState) {    
        super.onCreate(savedInstanceState);    
        mScroller = ScrollerCompat.create(this);  
        
        RelativeLayout mainlayout=new RelativeLayout(this);
        RelativeLayout.LayoutParams p1 = new RelativeLayout.LayoutParams    
        (RelativeLayout.LayoutParams.FILL_PARENT,RelativeLayout.LayoutParams.FILL_PARENT);        

        
        btn1 = new Button(this);         
        btn1.setText("btn1 ");         
       
        
        mylayout = new MyRelativeLayout(this);        
        mylayout.setBackgroundColor(Color.parseColor("#00000000"));  
        btn2 = new Button(this);         
        btn2.setText("btn2 in mylayout");  
        btn2.setOnClickListener(new OnClickListener(){    
            @Override    
            public void onClick(View v) {    
                    mScroller.startScroll(0, 0, 0, -200, 500);
                    mylayout.invalidate();
                    
                }    
        });   
        mylayout.addView(btn2); 
        //lay1.setLayerType(View.LAYER_TYPE_SOFTWARE, null);  
        mainlayout.addView(btn1);
        mainlayout.addView(mylayout,p1);
        
        this.setContentView(mainlayout, p1); 
       
    }    
  
	class MyRelativeLayout extends RelativeLayout    
	{    
	     public MyRelativeLayout(Context ctx)    
	     {  
	    	 super(ctx);    
	     }    
	         
	     @Override    
	        /**  
	         * Called by a parent to request that a child update its values for mScrollX  
	         * and mScrollY if necessary. This will typically be done if the child is  
	         * animating a scroll using a {@link android.widget.Scroller Scroller}  
	         * object.  
	         */    
	     public void computeScroll()     
	     {      
		    //Log.i("ly", this.toString() + " computeScroll-----------");    
		    if (mScroller.computeScrollOffset())//如果mScroller没有调用startScroll，这里将会返回false。    
		    {      
		        //因为调用computeScroll函数的是MyLinearLayout实例，    
		    	//所以调用scrollTo移动的将是该实例的孩子，也就是MyButton实例    
		        scrollTo(mScroller.getCurrX(), mScroller.getCurrY());  //view里的方法.duration时间内持续调用，也可用如下的layout代替，但是方向正好相反
		        //btn1.layout(mScroller.getCurrX(), btn1.getTop(), mScroller.getCurrX()+btn1.getWidth(), btn1.getBottom());
		        
		       // Log.i("ly", "getCurrX = " +  mScroller.getCurrX());    
		    
		        //继续让系统重绘    
		        //postInvalidate();  
		    }    
	    }    
	}    
}    

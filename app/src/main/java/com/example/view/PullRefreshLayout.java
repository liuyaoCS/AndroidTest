package com.example.view;




import com.example.androidtest.R;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.GestureDetectorCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.LinearInterpolator;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Scroller;



public class PullRefreshLayout extends ScrollView {

    private View topView;
    private View refreshView;
    private View mSearchBar;
    private View lstCards; 

    private android.animation.ObjectAnimator oa;

    private float lastDownX = 0;
    private float lastY = 0;
    private float lastDownY = 0;
    private float detalY = 0;
    private float detalDownY=0;
    
    OnRefreshListener mListener;
    public final int REFRESH_TIME_DELAY = 300; //刷新时间
    public final int SCROLL_DELAY = 50; //scroll完成状态轮询周期
    public final int MOVE_X_DISTANCE = 100;//卡片滑动距离最小阈值
    public final int MOVE_Y_DISTANCE = 300; //刷新距离最小阈值
    public final int HIDE_DISTANCE = 300;//头部背景隐藏距离
    
    private boolean isDrag =false;
    
    private Handler handler=null;
    private CheckTask task;
    

    private int ACTION_STATUS =0; //0:down 1:move 2:up

    public PullRefreshLayout(Context context) {
        super(context);
        init(context);
    }

    public PullRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PullRefreshLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }
    private void init(Context context){
    	handler=new Handler();
    	task=new CheckTask();
    	handler.postDelayed(task, SCROLL_DELAY);
   
    }
    class CheckTask implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			if(getScrollY()!=HIDE_DISTANCE){
				scrollTo(0, HIDE_DISTANCE);
				handler.postDelayed(task, SCROLL_DELAY);
				//topView.setVisibility(View.VISIBLE);
				//mSearchBar.setVisibility(View.VISIBLE);
			}else{				
				topView.setVisibility(View.VISIBLE);
				topView.scrollTo(0, 100);
				//mSearchBar.setVisibility(View.VISIBLE);
//				if(SharedPreferencesUtil.getInitAppFlag()){
//					if(SoAPP.mAppAllData==null){
//						SoAPP.mAppAllData = new AppAllData(SharedPreferencesUtil.getInitAppData());
//					}
//					if(SoAPP.isShowCard){
//						lstCards.setCardManageVisibility(SoAPP.mAppAllData.isShowCard());
//					}else{
//						lstCards.setCardManageVisibility(false);
//					}
//				}
			}
		}
    	
    }
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        setVerticalScrollBarEnabled(false);
        topView = findViewById(R.id.homeHeader);
        topView.setVisibility(View.INVISIBLE);       
        refreshView=findViewById(R.id.refreshview);
        topView.setVisibility(View.VISIBLE);
        lstCards=(View) findViewById(R.id.lstCards);
        
    }
    
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
            	ACTION_STATUS=0;
            	
            	lastDownX=ev.getX();
            
            	lastDownY= ev.getY();
                lastY= lastDownY;
                break;
            case MotionEvent.ACTION_MOVE:
     
            	if(Math.abs(lastDownX-ev.getX())>MOVE_X_DISTANCE){
            		return false;
            	}
            	break;
        }
        return super.onInterceptTouchEvent(ev);
    }
    @SuppressLint("ClickableViewAccessibility")
	@Override
    public boolean onTouchEvent(MotionEvent ev) {
  
        switch (ev.getAction()) {
        	case MotionEvent.ACTION_DOWN: 
        		ACTION_STATUS=0;
        		
                lastY = ev.getY();
                lastDownY= ev.getY();
                
                //DebugUtil.i(DebugUtil.TAG_PULLREFRESH,"ACTION_DOWN--lastDownY"+lastDownY);
        		break;
            case MotionEvent.ACTION_MOVE:
            	ACTION_STATUS=1;
            	
            	//DebugUtil.i(DebugUtil.TAG_PULLREFRESH,"ACTION_MOVE--ev.getY()= "+ev.getY()+" getScrollY()= "+getScrollY());
                if (getScrollY() >= 0 && getScrollY()<=HIDE_DISTANCE) {
                	
                	detalY = ev.getY() - lastY;
                	lastY=ev.getY();
                	
                	//DebugUtil.i(DebugUtil.TAG_PULLREFRESH,"ev.getY()="+ev.getY()+" detalY= "+detalY);
                	
                	detalDownY=ev.getY() - lastDownY;
                    if (detalDownY> 0) {
                    	isDrag=true;
                    	//DebugUtil.i(DebugUtil.TAG_PULLREFRESH,"detalDownY="+detalDownY);
                    	 if(detalDownY>MOVE_Y_DISTANCE){
                         	refreshView.setVisibility(View.VISIBLE);
                         }else{
                        	refreshView.setVisibility(View.INVISIBLE);
                         }
                        scrollBy(0,(int) -detalY/4);
                        return true;
                    }                   
                    
                } 

                break;
            case MotionEvent.ACTION_UP:
            	ACTION_STATUS=2;
            	
            	//DebugUtil.i(DebugUtil.TAG_PULLREFRESH,"ACTION_UP--");
                if (getScrollY() >= 0 && getScrollY()<=HIDE_DISTANCE) {   
                   reset(getScrollY(),HIDE_DISTANCE);
                   return true;
                }
                break;
        }
        return super.onTouchEvent(ev);
    }
    
    public void setT(int t) {        
        	scrollTo(0, t);
        	//DebugUtil.i(DebugUtil.TAG_PULLREFRESH,"scrollTo t="+t);
    }
   

    private void reset(int curY,int toY) {
        if (oa != null && oa.isRunning()) {
            return;
        }

        oa = ObjectAnimator.ofInt(this, "t", curY, toY);
        //DebugUtil.i(DebugUtil.TAG_PULLREFRESH,"curY="+curY+" toY="+toY);
        oa.setDuration(REFRESH_TIME_DELAY);
        oa.addListener(new AnimatorListener(){

			@Override
			public void onAnimationStart(Animator animation) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onAnimationEnd(Animator animation) {
				// TODO Auto-generated method stub
				if(detalDownY>MOVE_Y_DISTANCE){
					mListener.onRefresh();
				}
				isDrag=false;
				
			}


			@Override
			public void onAnimationRepeat(Animator animation) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onAnimationCancel(Animator animation) {
				// TODO Auto-generated method stub
				
			}});
        oa.start();

        
        if(detalDownY>MOVE_Y_DISTANCE){
        	ObjectAnimator animator = ObjectAnimator.ofFloat(refreshView, "rotation", 0f, 360f);  
        	animator.setDuration(REFRESH_TIME_DELAY);  
        	animator.setInterpolator(new LinearInterpolator());
        	animator.addListener(new AnimatorListener(){

				@Override
				public void onAnimationStart(Animator animation) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onAnimationEnd(Animator animation) {
					// TODO Auto-generated method stub
					refreshView.setVisibility(View.INVISIBLE);
				}

				@Override
				public void onAnimationCancel(Animator animation) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onAnimationRepeat(Animator animation) {
					// TODO Auto-generated method stub
					
				}});
        	animator.start(); 
        }
    }

    public void setOnRefreshListener(OnRefreshListener listener) {
        mListener = listener;
    }
    public interface OnRefreshListener {
        public void onRefresh();
    }
    
    private OnScrollChangedListener onScrollChangedListener;
    
    public interface OnScrollChangedListener{
        public void onScrollChanged(int x, int y, int oldxX, int oldY);
    }
    
    public void setOnScrollListener(OnScrollChangedListener onScrollChangedListener){
        this.onScrollChangedListener=onScrollChangedListener;
    }
   
    protected void onScrollChanged(int x, int y, int oldX, int oldY){
        super.onScrollChanged(x, y, oldX, oldY);
        //DebugUtil.i(DebugUtil.TAG_PULLREFRESH,"onScrollChanged oldY= "+oldY+" y= "+y);
        if(ACTION_STATUS==2 && y<this.HIDE_DISTANCE  && (oa==null || !oa.isRunning())){
        	scrollTo(0,HIDE_DISTANCE);
        }
        if(onScrollChangedListener!=null && (isDrag || !(ACTION_STATUS==2 && y<HIDE_DISTANCE))){
            onScrollChangedListener.onScrollChanged(x, y, oldX, oldY);
        }
    }
    
    public void setSearchBar(View searchBar){
    	mSearchBar=searchBar;
    }

}

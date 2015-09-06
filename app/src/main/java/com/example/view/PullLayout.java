package com.example.view;



import com.example.androidtest.R;
import com.example.helper.DisplayUtil;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.ScrollView;



public class PullLayout extends ScrollView {

    private View rl_top;

    private android.animation.ObjectAnimator oa;
    private float lastY = 0;
    private float detalY = 0;
    private int topHeight;
    
    OnRefreshListener mListener;
    public final int REFRESH_TIME_DELAY = 300;
    public final int HIDE_DISTANCE = DisplayUtil.Dp2Px(getContext(), 100);

    public PullLayout(Context context) {
        super(context);
    }

    public PullLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PullLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        setVerticalScrollBarEnabled(false);
        rl_top = findViewById(R.id.rl_top);

        rl_top.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
            @SuppressWarnings("deprecation")
            @Override
            public void onGlobalLayout() {
                rl_top.getViewTreeObserver().removeGlobalOnLayoutListener(this);
               
                topHeight = rl_top.getHeight();
                Log.i("pulllayout", "topHeight = "+topHeight);
                scrollTo(0, HIDE_DISTANCE);
                rl_top.getLayoutParams().height = topHeight;
            }
        });

    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
        	case MotionEvent.ACTION_DOWN:       	
                lastY = ev.getY();
        		break;
            case MotionEvent.ACTION_MOVE:
            	Log.i("pulllayout","getScrollY()="+getScrollY());
                if (getScrollY() >= 0 && getScrollY()<=HIDE_DISTANCE) {
                	detalY = ev.getY() - lastY;
                	Log.i("pulllayout","detalY="+detalY);
                    if (detalY > 0) {
                        setT((int) -detalY/3);
                        return true;
                    }
                    
                } else {
                	detalY = 0;
                    lastY = ev.getY();
                }
                break;
            case MotionEvent.ACTION_UP:
            	Log.i("pulllayout","lastY"+lastY);
                if (getScrollY() >= 0 && getScrollY()<=HIDE_DISTANCE) {   
                    reset((int) -detalY/3);
                    return true;
                }
                break;
        }
        return super.onTouchEvent(ev);
    }

    public void setT(int t) {
//        scrollTo(0, t);
        if (t < 0) {
            animatePull(t);
        }
    }
    private void animatePull(int t) {
        rl_top.getLayoutParams().height = topHeight - t;
        rl_top.requestLayout();
    }

    private void reset(int dy) {
        if (oa != null && oa.isRunning()) {
            return;
        }
        oa = ObjectAnimator.ofInt(this, "t", dy, 0);
        oa.setDuration(REFRESH_TIME_DELAY);
        oa.addListener(new AnimatorListener(){

			@Override
			public void onAnimationStart(Animator animation) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onAnimationEnd(Animator animation) {
				// TODO Auto-generated method stub
				mListener.onRefresh();
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
    }
    public void setOnRefreshListener(OnRefreshListener listener) {
        mListener = listener;
    }
    public interface OnRefreshListener {
        public void onRefresh();
    }
}

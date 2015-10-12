package com.example.experience;

import android.content.Context;
import android.graphics.Point;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by rmss on 2015/9/28.
 */
public class VDHLayout extends LinearLayout{
   private ViewDragHelper mViewDragHelper;
    private View mDragView,mAutobackView,mEdgeTrackView;
    private Point mAutoBackPoint=new Point();

    public VDHLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mViewDragHelper=ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                return child == mDragView || child == mAutobackView;
            }

            @Override
            public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
                super.onViewPositionChanged(changedView, left, top, dx, dy);
            }

            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                if(releasedChild==mAutobackView){
                    mViewDragHelper.settleCapturedViewAt(mAutoBackPoint.x,mAutoBackPoint.y);
                    invalidate();
                }
            }

            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
                if(left<getPaddingLeft()){
                    return getPaddingLeft();
                }
                if(left+child.getWidth()>getWidth()-getPaddingRight()){
                    return getWidth()-getPaddingRight()-child.getWidth();
                }
                Log.i("ly","left="+left);
                return left;
            }

            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {
                if(top<getPaddingTop()){
                    return getPaddingTop();
                }
                if(top+child.getHeight()>getHeight()-getPaddingBottom()){
                    return getHeight()-getPaddingBottom()-child.getHeight();
                }
                Log.i("ly","top="+top);
                return top;
            }
        });

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mViewDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
         mViewDragHelper.processTouchEvent(event);
         return true;
    }

    @Override
    public void computeScroll() {
        if(mViewDragHelper.continueSettling(true)){
            invalidate();
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        mAutoBackPoint.set(mAutobackView.getLeft(),mAutobackView.getRight());
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mDragView=getChildAt(0);
        mAutobackView=getChildAt(1);
        mEdgeTrackView=getChildAt(2);
    }
}

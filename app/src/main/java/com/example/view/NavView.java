package com.example.view;

import com.example.androidtest.R;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class NavView extends ViewGroup{
	private LinearLayout firstLineLayout;
	private LinearLayout secondLineLayout;
	private Button btn1,btn2;
	private Handler handler;
	private Context mContext;
	public NavView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
//		mContext=context;
//		init();
	}

	public NavView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
//		mContext=context;
//		init();
	}
	
	public void init(){
		
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec); //设置NavView的尺寸:setMeasuredDimension
		
		measureChildren(widthMeasureSpec, heightMeasureSpec); //都是递归调用，child的child就无需再调用measure了
		
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		int tt=t;
		for(int i=0;i<this.getChildCount();i++){
			View child=this.getChildAt(i);
			child.layout(l, tt, l+child.getMeasuredWidth(), tt+child.getMeasuredHeight());                                  //都是递归调用，child的child就无需再调用layout了，除非改变layout参数
																														    //如果布局设visible属性为gone，那么即使这里不用child.getMeasuredWidth()，而是设定值，也不会显示,
																															//因为gone不是影响的measure而是layout里面的setFrame
//			for(int j=0;j<((ViewGroup) child).getChildCount();j++){
//				View child_child=((ViewGroup)child).getChildAt(0);
//				child_child.item_adapter_layout(0, 0, child_child.getMeasuredWidth(), child_child.getMeasuredHeight());					//注意这四个参数都是相对父view的
//			}		

			tt+=child.getMeasuredHeight();
		}

	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
	}

	public void testFunc(){
		this.getChildAt(1).offsetTopAndBottom(-400);
		//this.getChildAt(1).scrollBy(0, -200);
	}
}

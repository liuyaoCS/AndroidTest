package com.example.activity;



import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.androidtest.R;
import com.example.helper.DisplayUtil;

public class AnimationActivity extends Activity {
	ImageView pic;
	Handler handler;
	LinearLayout layout;
	public static int count=1;

	/**
	 * test
	 * @param savedInstanceState
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scrollviewtest);
		layout=(LinearLayout) this.findViewById(R.id.linearLayout1);
		pic=(ImageView) this.findViewById(R.id.pic);
		
		final AnimationSet set=new AnimationSet(true);
		AlphaAnimation anim=new AlphaAnimation(1.0f,0.0f);
		set.addAnimation(anim);
		set.setDuration(300);
		set.setAnimationListener(new AnimationListener(){

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				pic.clearAnimation();
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}});
		set.setFillAfter(true);
		
		handler=new Handler(){

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				Log.i("ly", "px width "+pic.getWidth()+", height "+pic.getHeight());
				Log.i("ly", "dp width "+DisplayUtil.Px2Dp(AnimationActivity.this, pic.getWidth())+", height "+DisplayUtil.Px2Dp(AnimationActivity.this,pic.getHeight()));
				DisplayUtil.show(AnimationActivity.this);
				
				//pic.layout(100, pic.getTop(), 100+pic.getWidth(), pic.getBottom());
//				layout.removeView(pic);
//				layout.addView(pic, 0);
				
//				count++;
//				pic.layout(100*count, pic.getTop(), 100*count+pic.getWidth(), pic.getBottom());
				pic.startAnimation(set);
			}};
		
			
		//handler.sendEmptyMessageDelayed(0, 1000);
		pic.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				layout.removeView(pic);
				layout.addView(pic, 0);
				
				
				//pic.startAnimation(set);
				
				handler.sendEmptyMessageDelayed(0, 500);
				
			}
			
		});
		
	}
}

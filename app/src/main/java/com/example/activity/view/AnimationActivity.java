package com.example.activity.view;



import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
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
	 * test1
	 * @param savedInstanceState
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scrollviewtest);
		layout=(LinearLayout) this.findViewById(R.id.linearLayout1);
		pic=(ImageView) this.findViewById(R.id.pic);
		

		AlphaAnimation anim=new AlphaAnimation(1.0f,0.0f);
		//anim.setFillAfter(true);

		final AnimationSet set=new AnimationSet(true);
		set.addAnimation(anim);
		set.setFillAfter(true);
		set.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				pic.clearAnimation();
			}

			@Override
			public void onAnimationRepeat(Animation animation) {

			}
		});


		pic.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				layout.removeView(pic);
				layout.addView(pic, 0);


				pic.startAnimation(set);
			}
			
		});
		
	}
}

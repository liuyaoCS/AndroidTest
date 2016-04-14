package com.example.activity.test;

import java.util.ArrayList;
import java.util.List;

import com.example.androidtest.R;
import com.example.helper.ScreenUtils;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


/**
 * @author liuyao , huangyongzheng
 *
 */
public class IqiyiMainActivity extends Activity {

	private ImageView sliding;
	private ImageView v1_ball,v1_people, v1_cloud1,v1_plane,v1_line, v1_text1,v1_text2;
	private ImageView v2_glass,v2_cloud, v2_rotate,v2_star, v2_text1,v2_text2;
	private ImageView v3_bk,v3_moon,v3_lamp,v3_light, v3_cloud1, v3_cloud2,v3_text1,v3_text2,v3_button_light;
	private Button v3_button,v3_list_button,v3_poster_button;

	private AnimationDrawable v1_flag_animationDrawable,v1_cloud_animationDrawable;
	private Animation animationV1_Ball,animationV1_People,animationV1_Text1,animationV1_Text2,animationV1_Plane,animationV1_Line,animationV1_Cloud1;
	private Animation animationV2_Glass,animationV2_Cloud1,animationV2_Cloud2,animationV2_Star,animationV2_Rotate,animationV2_Text1,animationV2_Text2;
	private Animation animationV3_bk,animationV3_moon,animationV3_cloud1,animationV3_cloud2,animationV3_lamp,animationV3_light,animationV3_text1,animationV3_text2,animationV3_list_button,animationV3_poster_button,animationV3_button,animationV3_button_light;
	
	private long T_v1_ball=500,T_v1_people=500,T_v1_cloud1=500,T_v1_plane=1200,T_v1_line=1200,T_v12_text=800;
	private long T_v2_glass=500,T_v2_rotate=500,T_v2_cloud1=1000,T_v2_cloud2=1500,T_v2_star=500;
	private long T_v3_moon=500,T_v3_light=1000,T_v3_lamp=600,T_v3_cloud1=1500,T_v3_cloud2=1500,T_v3_text=1000,T_v3_button=1000,T_v3_button_light=300,T_v3_mode=1000;

	private ViewPager mPager;
	private MyPagerAdapter mAdapter;
	private List<View> pagers = new ArrayList<View>();
	private int preIndex = 0;
	int list_index=0,poster_index=0;
	int[] list={com.example.androidtest.R.drawable.v3_list_notchecked,R.drawable.v3_list_checked};
	int[] poster={R.drawable.v3_poster_checked,R.drawable.v3_poster_notchecked};
	
	RelativeLayout slidingdot_layout;
	int slidingLeft=0;
	int slidingWidth=0;
	
	int WidthPx=720;
	int HeightPx=1080;
	
	RelativeLayout v1_earth_layout,v1_boo_layout,v1_cloud1_layout,v1_plane_layout,v1_line_layout;
	RelativeLayout v2_glass_layout,v2_star_layout,v2_rotate_layout,v2_cloud_layout;
	RelativeLayout v3_moon_layout,v3_lamp_layout,v3_light_layout,v3_cloud1_layout,v3_cloud2_layout;
	LinearLayout v3_mode_layout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_iqiyi_main);

		Display mDisplay = getWindowManager().getDefaultDisplay();
		WidthPx = mDisplay.getWidth();
		HeightPx = mDisplay.getHeight();
		
		mPager = (ViewPager) findViewById(R.id.pager);
		slidingdot_layout=(RelativeLayout) findViewById(R.id.slidingdot_layout);
		slidingdot_layout.setVisibility(View.INVISIBLE);
		sliding=(ImageView) findViewById(R.id.sliding);
		sliding.setVisibility(View.INVISIBLE);
		
		View view1 = LayoutInflater.from(this).inflate(
				R.layout.layout1, null);
		v1_ball= (ImageView) view1.findViewById(R.id.v1_earth);
		v1_people = (ImageView) view1.findViewById(R.id.v1_boo);
		v1_plane = (ImageView) view1.findViewById(R.id.v1_plane);
		v1_line = (ImageView) view1.findViewById(R.id.v1_line);
		v1_cloud1 = (ImageView) view1.findViewById(R.id.v1_cloud1);
		v1_text1= (ImageView) view1.findViewById(R.id.v1_text1);
		v1_text2= (ImageView) view1.findViewById(R.id.v1_text2);
		
		
		v1_ball.setVisibility(View.INVISIBLE);
		v1_people.setVisibility(View.INVISIBLE);
		v1_earth_layout=(RelativeLayout) view1.findViewById(R.id.v1_earth_layout );
		v1_boo_layout=(RelativeLayout) view1.findViewById(R.id.v1_boo_layout );
		v1_cloud1_layout=(RelativeLayout) view1.findViewById(R.id.v1_cloud1_layout );	
		v1_plane_layout=(RelativeLayout) view1.findViewById(R.id.v1_plane_layout );
		v1_line_layout=(RelativeLayout) view1.findViewById(R.id.v1_line_layout );
				
		pagers.add(view1);	
		
		View view2 = LayoutInflater.from(this).inflate(
				R.layout.layout2, null);
		v2_glass= (ImageView) view2.findViewById(R.id.v2_glass);
		v2_star = (ImageView) view2.findViewById(R.id.v2_star);
		v2_rotate = (ImageView) view2.findViewById(R.id.v2_rotate);
		v2_cloud = (ImageView) view2.findViewById(R.id.v2_cloud);
		v2_text1= (ImageView) view2.findViewById(R.id.v2_text1);
		v2_text2= (ImageView) view2.findViewById(R.id.v2_text2);
		pagers.add(view2);

		View view3 = LayoutInflater.from(this).inflate(
				R.layout.layout3, null);
		v3_bk= (ImageView) view3.findViewById(R.id.v3_bk);
		v3_moon= (ImageView) view3.findViewById(R.id.v3_moon);
		v3_cloud1 = (ImageView) view3.findViewById(R.id.v3_cloud1);
		v3_cloud2 = (ImageView) view3.findViewById(R.id.v3_cloud2);
		v3_light = (ImageView) view3.findViewById(R.id.v3_light);
		v3_lamp = (ImageView) view3.findViewById(R.id.v3_lamp);
		v3_text1= (ImageView) view3.findViewById(R.id.v3_text1);
		v3_text2= (ImageView) view3.findViewById(R.id.v3_text2);
		v3_mode_layout= (LinearLayout) view3.findViewById(R.id.v3_mode_layout);
		v3_poster_button=(Button) view3.findViewById(R.id.v3_poster_button);
		v3_poster_button.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				v3_list_button.setBackgroundResource(list[list_index++]);
				v3_poster_button.setBackgroundResource(poster[poster_index++]);
				list_index=list_index%2;
				poster_index=poster_index%2;
			}
			
		});
		v3_list_button=(Button) view3.findViewById(R.id.v3_list_button);
		v3_list_button.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				v3_list_button.setBackgroundResource(list[list_index++]);
				v3_poster_button.setBackgroundResource(poster[poster_index++]);
				list_index=list_index%2;
				poster_index=poster_index%2;
			}
			
		});
		v3_button_light = (ImageView) view3.findViewById(R.id.v3_button_light);
		v3_button = (Button) view3.findViewById(R.id.v3_button);
		v3_button.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				System.out.println(v3_button.getLeft());
			}
			
		});
		
		v3_lamp_layout=(RelativeLayout) view3.findViewById(R.id.v3_lamp_layout );
		v3_light_layout=(RelativeLayout) view3.findViewById(R.id.v3_light_layout );
		pagers.add(view3);
		
		
		mAdapter = new MyPagerAdapter();
		mPager.setAdapter(mAdapter);
		mPager.setOnPageChangeListener(new MyPageChangeListener());
		
		initAnimation();
		new Handler(){}.postDelayed(new Runnable(){

			@Override
			public void run() {
				//动态设置x坐标
				sliding.setVisibility(View.VISIBLE);
				slidingLeft=sliding.getLeft();
				slidingWidth=sliding.getWidth();
				slidingdot_layout.setVisibility(View.VISIBLE);
				slidingdot_layout.setPadding(slidingLeft,0,0, 0);
				
				int v1_left=v1_ball.getLeft();	
				int v1_boo_left=ScreenUtils.widthAdapt(114, WidthPx);
				int v1_plane_left=ScreenUtils.widthAdapt(227, WidthPx);
				int v1_line_left=ScreenUtils.widthAdapt(127, WidthPx);
				v1_boo_layout.setPadding(v1_left+v1_boo_left, 0, 0, 0);
				v1_cloud1_layout.setPadding(v1_left+v1_boo_left, 0, 0, 0);
				v1_plane_layout.setPadding(v1_left+v1_plane_left, 0, 0, 0);
				v1_line_layout.setPadding(v1_left+v1_line_left, 0, 0, 0);

				animal(0);
			}}, 1000);
		
	}
    /**
     * 初始化动画
     */
	private void initAnimation(){
		animationV1_Ball = AnimationUtils.loadAnimation(IqiyiMainActivity.this, R.anim.v1_ball_anim);				
		animationV1_People = AnimationUtils.loadAnimation(IqiyiMainActivity.this, R.anim.v1_people_anim);				
		animationV1_Text1 = AnimationUtils.loadAnimation(IqiyiMainActivity.this, R.anim.v12_text_anim);				
		animationV1_Text2 = AnimationUtils.loadAnimation(IqiyiMainActivity.this, R.anim.v12_text_anim);						
		animationV1_Plane = AnimationUtils.loadAnimation(IqiyiMainActivity.this, R.anim.v1_plane_anim);	
		animationV1_Line = AnimationUtils.loadAnimation(IqiyiMainActivity.this, R.anim.v1_line_anim);	
		animationV1_Cloud1 = AnimationUtils.loadAnimation(IqiyiMainActivity.this, R.anim.v1_cloud_anim1);
		
		animationV1_Ball.setDuration(T_v1_ball);
		animationV1_People.setDuration(T_v1_people);
		animationV1_Text1.setDuration(T_v12_text);
		animationV1_Text2.setDuration(T_v12_text);
		animationV1_Plane.setDuration(T_v1_plane);
		animationV1_Line.setDuration(T_v1_line);
		animationV1_Cloud1.setDuration(T_v1_cloud1);
	
				
		animationV2_Glass = AnimationUtils.loadAnimation(IqiyiMainActivity.this, R.anim.v2_glass_anim);
		animationV2_Star = AnimationUtils.loadAnimation(IqiyiMainActivity.this, R.anim.v2_star_anim);
		animationV2_Cloud1 = AnimationUtils.loadAnimation(IqiyiMainActivity.this, R.anim.v2_cloud_anim1);				
		animationV2_Cloud2 = AnimationUtils.loadAnimation(IqiyiMainActivity.this, R.anim.v2_cloud_anim2);		
		animationV2_Rotate = AnimationUtils.loadAnimation(IqiyiMainActivity.this, R.anim.v2_rotate_anim);				
		animationV2_Text1 = AnimationUtils.loadAnimation(IqiyiMainActivity.this, R.anim.v12_text_anim);		
		animationV2_Text2 = AnimationUtils.loadAnimation(IqiyiMainActivity.this, R.anim.v12_text_anim);
		
		animationV2_Glass.setDuration(T_v2_glass);
		animationV2_Star.setDuration(T_v2_star);
		animationV2_Cloud1.setDuration(T_v2_cloud1);
		animationV2_Cloud2.setDuration(T_v2_cloud2);
		animationV2_Rotate.setDuration(T_v2_rotate);
		animationV2_Text1.setDuration(T_v12_text);
		animationV2_Text2.setDuration(T_v12_text);
		
						 
		animationV3_moon = AnimationUtils.loadAnimation(IqiyiMainActivity.this, R.anim.v3_moon_anim);
		animationV3_bk = AnimationUtils.loadAnimation(IqiyiMainActivity.this, R.anim.v3_moon_anim);
		animationV3_cloud1 = AnimationUtils.loadAnimation(IqiyiMainActivity.this, R.anim.v3_cloud1_anim);
		animationV3_cloud2 = AnimationUtils.loadAnimation(IqiyiMainActivity.this, R.anim.v3_cloud2_anim);
		animationV3_lamp = AnimationUtils.loadAnimation(IqiyiMainActivity.this, R.anim.v3_lamp_anim);	
		animationV3_light = AnimationUtils.loadAnimation(IqiyiMainActivity.this, R.anim.v3_light_anim);				
		animationV3_text1 = AnimationUtils.loadAnimation(IqiyiMainActivity.this, R.anim.v3_text_anim);				
		animationV3_text2 = AnimationUtils.loadAnimation(IqiyiMainActivity.this, R.anim.v3_text_anim);
		animationV3_list_button = AnimationUtils.loadAnimation(IqiyiMainActivity.this, R.anim.v3_text_anim);
		animationV3_poster_button = AnimationUtils.loadAnimation(IqiyiMainActivity.this, R.anim.v3_text_anim);
		animationV3_button = AnimationUtils.loadAnimation(IqiyiMainActivity.this, R.anim.v3_button_anim);
		animationV3_button_light = AnimationUtils.loadAnimation(IqiyiMainActivity.this, R.anim.v3_button_light_anim);
		
		animationV3_moon.setDuration(T_v3_moon);
		animationV3_bk.setDuration(T_v3_moon);
		animationV3_cloud1.setDuration(T_v3_cloud1);
		animationV3_cloud2.setDuration(T_v3_cloud2);
		animationV3_light.setDuration(T_v3_light);
		animationV3_lamp.setDuration(T_v3_lamp);
		animationV3_text1.setDuration(T_v3_text);
		animationV3_text2.setDuration(T_v3_text);
		animationV3_list_button.setDuration(T_v3_mode);
		animationV3_poster_button.setDuration(T_v3_mode);
		animationV3_button.setDuration(T_v3_button);
		animationV3_button_light.setDuration(T_v3_button_light);
		
	}


	final Handler handler=new Handler(){
		@Override
		public void handleMessage(Message msg) {
			switch(msg.what){
			case 0x11:
					v1_people.setImageResource(R.drawable.v1_flag_frame_animation);
					v1_flag_animationDrawable = (AnimationDrawable) v1_people
							.getDrawable();
					v1_flag_animationDrawable.start();
					
					v1_cloud1.setImageResource(R.drawable.v1_cloud_frame_animation);
					v1_cloud1.setVisibility(View.VISIBLE);	
					v1_cloud_animationDrawable = (AnimationDrawable) v1_cloud1
							.getDrawable();
					v1_cloud_animationDrawable.getFrame(0).setAlpha(155);
					v1_cloud_animationDrawable.getFrame(5).setAlpha(125);
					
					v1_cloud_animationDrawable.start();
										
					
					v1_text1.setVisibility(View.VISIBLE);
					v1_text1.startAnimation(animationV1_Text1);
					
					v1_text2.setVisibility(View.VISIBLE);
					animationV1_Text2.setStartOffset(T_v12_text/2);
					v1_text2.startAnimation(animationV1_Text2);
					break;
				case 0x12:
										
					v1_plane.setVisibility(View.VISIBLE);
					v1_plane.startAnimation(animationV1_Plane);
					
					v1_line.setVisibility(View.VISIBLE); 
					v1_line.startAnimation(animationV1_Line);
					break;
					
				case 0x21:
					v2_star.setVisibility(View.VISIBLE);
					v2_star.startAnimation(animationV2_Star);
					
					v2_rotate.setVisibility(View.VISIBLE);
					v2_rotate.startAnimation(animationV2_Rotate);
					
					v2_cloud.setVisibility(View.VISIBLE);
					v2_cloud.startAnimation(animationV2_Cloud1);
					
					v2_text1.setVisibility(View.VISIBLE);
					v2_text2.setVisibility(View.VISIBLE);
					v2_text1.startAnimation(animationV2_Text1);
					animationV2_Text2.setStartOffset(T_v12_text/2);
					v2_text2.startAnimation(animationV2_Text2);
					break;
					
				case 0x22:
					v2_cloud.startAnimation(animationV2_Cloud2);
					break;
				
				case 0x31:
					v3_cloud1.setVisibility(View.VISIBLE);
					v3_cloud2.setVisibility(View.VISIBLE);
					v3_cloud1.startAnimation(animationV3_cloud1);
					v3_cloud2.startAnimation(animationV3_cloud2);
					
					v3_text1.setVisibility(View.VISIBLE);
					v3_text1.startAnimation(animationV3_text1);
					v3_text2.setVisibility(View.VISIBLE);
					animationV3_text2.setStartOffset(T_v3_text/2);
					v3_text2.startAnimation(animationV3_text2);
					break;
					
				case 0x32:
					v3_light.setVisibility(View.VISIBLE);						
					v3_light.startAnimation(animationV3_light);
					v3_lamp.setVisibility(View.VISIBLE);						
					v3_lamp.startAnimation(animationV3_lamp);
					break;
					
				case 0x33:
					v3_mode_layout.setVisibility(View.VISIBLE);	
					v3_list_button.startAnimation(animationV3_list_button);
					v3_poster_button.startAnimation(animationV3_poster_button);
					
					v3_button.setVisibility(View.VISIBLE);						
					v3_button.startAnimation(animationV3_button);
//					v3_button_light.setVisibility(View.VISIBLE);						
//					v3_button_light.startAnimation(animationV3_button);
					break;
					
				default:
					break;
					
				
			}
		}
	};
	/**
	 * 每页的动画
	 * @param position
	 */
	private void animal(int position) {
		
		//int d=ScreenUtils.widthAdapt(1, WidthPx);
		slidingdot_layout.setPadding(slidingLeft+1+position*slidingWidth/3,0,0, 0);
		
		switch (position) {
		case 0:

			handler.removeMessages(0x21);
			handler.removeMessages(0x22);
			handler.removeMessages(0x31);
			handler.removeMessages(0x32);
			handler.removeMessages(0x33);
			
			clearRunninAnimatation(v3_button,v3_button_light,v3_cloud1,v3_cloud2,v3_lamp,v3_light,v3_text1,v3_text2,v3_mode_layout,v3_moon,v3_bk);
			clearRunninAnimatation(v2_cloud,v2_rotate,v2_text1,v2_text2,v2_glass,v2_star);
			
			v1_ball.startAnimation(animationV1_Ball);
			v1_people.startAnimation(animationV1_People);
			
			handler.sendEmptyMessageDelayed(0x11, T_v1_ball);
			handler.sendEmptyMessageDelayed(0x12, T_v1_ball+250);
		
			break;
		case 1:
	
			handler.removeMessages(0x11);
			handler.removeMessages(0x12);
			handler.removeMessages(0x31);
			handler.removeMessages(0x32);
			handler.removeMessages(0x33);
			
			clearRunninAnimatation(v3_button,v3_button_light,v3_cloud1,v3_cloud2,v3_lamp,v3_light,v3_text1,v3_text2,v3_mode_layout,v3_moon,v3_bk);
			clearRunninAnimatation(v1_plane , v1_line, v1_text1 , v1_text2,v1_ball,v1_people);
			v1_cloud_animationDrawable.stop();
			v1_cloud1.setVisibility(View.INVISIBLE);
							
			v2_glass.startAnimation(animationV2_Glass);
			
			handler.sendEmptyMessageDelayed(0x21, T_v2_glass);
			handler.sendEmptyMessageDelayed(0x22, T_v2_glass+T_v2_cloud1);
		
			break;
			
		case 2:
	
			int v3_left=v3_moon.getLeft();
			int v3_lamp_left=ScreenUtils.widthAdapt(12, WidthPx);
			v3_light_layout.setPadding(v3_left+v3_lamp_left, 0, 0, 0);
			v3_lamp_layout.setPadding(v3_left+v3_lamp_left, 0, 0, 0);
			
			handler.removeMessages(0x11);
			handler.removeMessages(0x12);
			handler.removeMessages(0x21);
			handler.removeMessages(0x22);
			
			clearRunninAnimatation(v2_cloud,v2_rotate,v2_text1,v2_text2,v2_glass,v2_star);
			clearRunninAnimatation(v1_plane , v1_line, v1_text1 , v1_text2,v1_ball,v1_people);
			v1_cloud_animationDrawable.stop();
			v1_cloud1.setVisibility(View.INVISIBLE);
									
			v3_moon.startAnimation(animationV3_moon);
			v3_bk.startAnimation(animationV3_bk);
	
			handler.sendEmptyMessageDelayed(0x31, T_v3_moon);
			handler.sendEmptyMessageDelayed(0x32, T_v3_moon+T_v3_cloud1/3);
			handler.sendEmptyMessageDelayed(0x33, T_v3_moon+T_v3_text);
	
		default:
			break;
		}

		preIndex = position;
	}
	/**
	 * 清空动画
	 * @param views
	 */
	private void clearRunninAnimatation(View...views){
		for(View view : views){
			if(view!=null){
				Animation animation = view.getAnimation();
				if(animation!=null){
					
					animation.cancel();
					animation.reset();
					
				}
				view.clearAnimation();
				
				
				view.setVisibility(View.INVISIBLE);
			}
		}
	}
	private class MyPageChangeListener implements OnPageChangeListener{

		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onPageSelected(int arg0) {
			// TODO Auto-generated method stub
			animal(arg0);
		}
		
	}
	
	private class MyPagerAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return pagers.size();
		}

		@Override
		public boolean isViewFromObject(View view, Object o) {
			return view == o;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {

			container.addView(pagers.get(position));
			return pagers.get(position);

		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}

		@Override
		public void restoreState(Parcelable state, ClassLoader loader) {
			// TODO Auto-generated method stub
			super.restoreState(state, loader);

		}

		@Override
		public Parcelable saveState() {
			// TODO Auto-generated method stub
			return super.saveState();
		}

	}

}

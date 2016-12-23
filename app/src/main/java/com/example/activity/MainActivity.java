package com.example.activity;


import cn.jpush.android.api.JPushInterface;

import com.example.activity.function.ButterKnifeTestActivity;
import com.example.activity.function.DataBindingTestActivity;
import com.example.activity.function.GreenDAOActivity;
import com.example.activity.function.Permission2Activity;
import com.example.activity.function.PermissionActivity;
import com.example.activity.function.WebViewUploadFileActivity;
import com.example.activity.support.ActionbarActivity;
import com.example.activity.support.CardViewActivity;
import com.example.activity.support.SwipeRefreshLayoutActivity;
import com.example.activity.view.Animation2Activity;
import com.example.activity.view.Animation3Activity;
import com.example.activity.view.AnimationActivity;
import com.example.activity.view.EventBusActivity;
import com.example.activity.view.FiveEightActivity;
import com.example.activity.view.GifActivity;
import com.example.activity.view.IqiyiMainActivity;
import com.example.activity.function.JSActivity;
import com.example.activity.view.MyViewActivity;
import com.example.activity.view.PathMenuMainActivity;
import com.example.activity.view.PullLayoutActivity;
import com.example.activity.view.PullRefreshMainActivity;
import com.example.activity.view.RecyclerViewActivity;
import com.example.activity.view.ScrollerActivity;
import com.example.activity.view.ShaderActivity;
import com.example.activity.view.SurfaceActivity;
import com.example.activity.view.VideoActivity;
import com.example.activity.view.ViewScrollActivity;
import com.example.activity.view.WaveActivity;
import com.example.androidtest.R;
import com.example.activity.function.AdaptationActivity;
import com.example.activity.function.AsyncActivity;
import com.example.activity.function.DraggerViewActivity;
import com.example.activity.function.FragmentTestActivity;
import com.example.activity.view.MovieRecorderActivity;
import com.example.activity.view.MovieRecorderActivity2;
import com.example.activity.function.NetActivity;
import com.example.activity.function.ResourceActivity;
import com.example.activity.function.SocketActivity;
import com.example.activity.view.VideoViewActivity;
import com.example.activity.view.ViewDragHelperTestActivity;
import com.example.activity.view.ViewTestActivity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity{

	ListView list;
	TextView tvHint;
	Item[] datasets;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_main);
		init();
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		JPushInterface.onResume(this);
	}
	

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		JPushInterface.onPause(this);
	}

	private void init(){
		tvHint = (TextView) findViewById(R.id.tv_hint);
		
		Animation ani = new AlphaAnimation(0f, 1f);
		ani.setDuration(1500);
		ani.setRepeatMode(Animation.REVERSE);
		ani.setRepeatCount(Animation.INFINITE);
		tvHint.startAnimation(ani);
		
		datasets=new Item[]{
				new Item("common",CommonTestActivity.class),
				new Item("fragment",FragmentTestActivity.class),
				new Item("resource",ResourceActivity.class),
				new Item("Adaptation",AdaptationActivity.class),
				new Item("permission",PermissionActivity.class),
				new Item("permission2",Permission2Activity.class),
				/*net test*/
				new Item("js",JSActivity.class),
				new Item("WebViewUploadFileActivity",WebViewUploadFileActivity.class),
				new Item("async",AsyncActivity.class),
				new Item("eventBus",EventBusActivity.class),
				new Item("socket",SocketActivity.class),
				new Item("net",NetActivity.class),
				/*data test*/
				new Item("GreenDAOActivity",GreenDAOActivity.class),
				new Item("ButterKnifeTestActivity",ButterKnifeTestActivity.class),
				new Item("DataBindingTestActivity",DataBindingTestActivity.class),
				/*view test*/
				new Item("RecyclerViewActivity",RecyclerViewActivity.class),
				new Item("animation",AnimationActivity.class),new Item("animation2",Animation2Activity.class),new Item("animation3",Animation3Activity.class),
				new Item("scroller",ScrollerActivity.class),new Item("ViewScroll",ViewScrollActivity.class),
				new Item("myview",MyViewActivity.class),
				new Item("viewtest",ViewTestActivity.class),
				new Item("draggerviewtest", DraggerViewActivity.class),
				new Item("ViewDragHelperTest", ViewDragHelperTestActivity.class),
				new Item("gif",GifActivity.class),
				new Item("photo",SurfaceActivity.class),
				new Item("record",MovieRecorderActivity.class),
				new Item("record2",MovieRecorderActivity2.class),
				new Item("video",VideoActivity.class),
				new Item("videoview-rtsp",VideoViewActivity.class),
				new Item("shader",ShaderActivity.class),new Item("wave",WaveActivity.class),
				new Item("iqiyi",IqiyiMainActivity.class),
				new Item("58",FiveEightActivity.class),
				new Item("pathmenu",PathMenuMainActivity.class),
				new Item("pulllayout",PullLayoutActivity.class),
				new Item("pullrefresh",PullRefreshMainActivity.class),
				new Item("ActionbarActivity",ActionbarActivity.class),
				new Item("SwipeRefreshLayoutActivity",SwipeRefreshLayoutActivity.class),
				new Item("CardViewActivity", CardViewActivity.class)
				};
		
		list=(ListView) findViewById(R.id.list);
		list.setAdapter(new BaseAdapter(){

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return datasets.length;
			}

			@Override
			public Object getItem(int position) {
				// TODO Auto-generated method stub
				return datasets[position];
			}

			@Override
			public long getItemId(int position) {
				// TODO Auto-generated method stub
				return position;
			}

			@Override
			public View getView(final int position, View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				ViewHolder holder=null;
				if(convertView==null){
					holder=new ViewHolder();
					convertView=LayoutInflater.from(MainActivity.this).inflate(R.layout.item_layout, null);
					holder.tv=(TextView) convertView.findViewById(R.id.text);
					convertView.setTag(holder);
				}else{
					holder=(ViewHolder) convertView.getTag();
				}
				holder.tv.setText(datasets[position].name);
				holder.tv.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent it=new Intent();
						it.setClass(MainActivity.this,datasets[position].className);
						it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						MainActivity.this.startActivity(it);
					}});
				return convertView;
			}});

	}
   
}  
class Item{
	public String name;
	public Class<? extends Activity> className ;
	public Item(String name, Class<? extends Activity> className) {
		super();
		this.name = name;
		this.className = className;
	}
	
}
class ViewHolder{
	TextView tv;
}
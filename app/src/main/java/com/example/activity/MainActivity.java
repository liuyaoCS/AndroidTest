package com.example.activity;


import cn.jpush.android.api.JPushInterface;

import com.example.androidtest.R;
import com.example.experience.AdapterActivity;
import com.example.experience.AsyncActivity;
import com.example.experience.DraggerViewActivity;
import com.example.experience.FragmentTestActivity;
import com.example.experience.MovieRecorderActivity;
import com.example.experience.MovieRecorderActivity2;
import com.example.experience.MyActivity;
import com.example.experience.NetActivity;
import com.example.experience.ResourceActivity;
import com.example.experience.SocketActivity;
import com.example.experience.VideoViewActivity;
import com.example.experience.ViewDragHelperTestActivity;
import com.example.experience.ViewTestActivity;
import com.umeng.analytics.MobclickAgent;
import com.umeng.update.UmengUpdateAgent;

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
		UmengUpdateAgent.update(this);
		init();

		
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		JPushInterface.onResume(this);
		MobclickAgent.onResume(this);
	}
	

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		JPushInterface.onPause(this);
		MobclickAgent.onPause(this);
	}

	private void init(){
		tvHint = (TextView) findViewById(R.id.tv_hint);
		
		Animation ani = new AlphaAnimation(0f, 1f);
		ani.setDuration(1500);
		ani.setRepeatMode(Animation.REVERSE);
		ani.setRepeatCount(Animation.INFINITE);
		tvHint.startAnimation(ani);
		
		datasets=new Item[]{
				new Item("组件",MyActivity.class),
				new Item("fragment",FragmentTestActivity.class),
				new Item("resource",ResourceActivity.class),
				new Item("adapter",AdapterActivity.class),
				new Item("common",CommonActivity.class),
				new Item("js",JSActivity.class),
				new Item("async",AsyncActivity.class),
				new Item("eventBus",EventBusActivity.class),
				new Item("socket",SocketActivity.class),
				new Item("umShare",UmShareActivity.class),
				new Item("net",NetActivity.class),
				/*new Item("webview",WebViewActivity.class),new Item("webview2",WebView2Activity.class),new Item("webviewcache",WebViewCacheActivity.class),*/
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
				new Item("pullrefresh",PullRefreshMainActivity.class)};
		
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
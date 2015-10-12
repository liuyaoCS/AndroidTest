package com.example.activity;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.androidtest.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.net.UrlQuerySanitizer;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.ContactsContract;
import android.telephony.PhoneNumberUtils;
import android.text.Html;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class CommonActivity extends Activity {
	Handler handler;
	ImageView id4;
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_common);
		Log.i("ly", "commonActivity oncreate");

		parseIntent();
		
		String str1=null;
        String str2="";
        Log.i("ly", "empty? "+TextUtils.isEmpty(str1)+TextUtils.isEmpty(str2));
        CommonActivity.this.getApplication();
        
        //Html	
        TextView view=(TextView) this.findViewById(R.id.view);
        
        view.setText(Html.fromHtml("<u>今天<b>礼拜</b>五</u>"));
        
        EditText edit=(EditText) this.findViewById(R.id.edit);
        edit.setFocusable(true);
        edit.setError("test err");
        
        Log.i("ly", "version sdk:"+Build.VERSION.SDK_INT);
        
        
        String res=PhoneNumberUtils.convertKeypadLettersToDigits("1-2ly");
        Log.i("ly", "convert: "+res);
        
        String dateStr = DateUtils.formatDateTime(this, System.currentTimeMillis(), DateUtils.FORMAT_SHOW_DATE);
        Log.i("ly", "date: "+dateStr);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");         
        Log.i("ly", "date2: "+dateFormat.format(new Date()));
        
        String nu=PhoneNumberUtils.formatNumber("136-8310-4106");
        Log.i("ly", "tel: "+nu);
        
        File file=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
        Log.i("ly", file.getName()+" music path: "+file.getPath());
        
        //ActivityManager.clearApplicationUserData();
        
        ViewGroup vg=new LinearLayout(this);
        //vg.setBackgroundDrawable(background);
        ImageView iv=new ImageView(this);
        vg.setBackgroundColor(Color.parseColor("#00122333"));
        BitmapDrawable bd=new BitmapDrawable(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher1));
        
        handler=new Handler();
        handler.postDelayed(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				new Thread(){

					@Override
					public void run() {
						// TODO Auto-generated method stub
						while(true){
							try {
								Thread.currentThread().sleep(500);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							//System.out.println("time::"+System.currentTimeMillis());
						}
					}}.start();
			}}, 100);
        
        String url="chinaso.app://?action=ShowWeb&url=http%3A%2F%2Fm.chinaso.com%2F%2Fxiangqing%2F20150602%2F1000200032830241433208777196961800_1.html&title=%E6%96%B0%E9%97%BB";
        //String url="http%3A%2F%2Fmobile.baidu.com%2F%23%2Fitem%3Fdocid%3D7840100";
        
        try {
			//url = URLDecoder.decode(url, "UTF-8");
			Log.i("ly", "url=="+url);

			Uri test=Uri.parse(url);
			String title=test.getQueryParameter("title");
			Log.i("ly", "title="+title);
			
//			String mUriStr = "http://www.java2s.com:8080/yourpath/fileName.htm?stove=10&path=32&id#harvic";  
//			Uri mUri = Uri.parse(mUriStr);  
//			Log.d("ly","getQueryParameter(\"stove\"):"+mUri.getQueryParameter("stove"));  
//			Log.d("ly","getQueryParameter(\"id\"):"+mUri.getQueryParameter("id"));  
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        
        String strURL = "http://zhidao.baidu.com/link?city=beijing";
        Uri uri=Uri.parse(strURL);
        String city=uri.getQueryParameter("city");
        System.out.println(city);
//        Pattern p = Pattern.compile(city); 
//        Matcher m = p.matcher(strURL);
//        System.out.println(m.replaceAll("cangzhou"));
        
        
        //System.out.println(isValidUrl(null));
        System.out.println(isValidUrl(""));
        System.out.println(isValidUrl(strURL));
        System.out.println(isValidUrl("ssd**s"));
        
        File file1=new File(Environment.getExternalStorageDirectory(),"test.mp4");
        MediaPlayer mediaPlayer=new MediaPlayer();

       // mediaPlayer.setDisplay(surfaceView.getHolder());//设置video影片以surfaceviewholder播放
        try {
        	mediaPlayer.reset();//重置为初始状态
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);//设置音乐流的类型
			mediaPlayer.setDataSource(file1.getAbsolutePath());
			mediaPlayer.prepare();//缓冲
		    mediaPlayer.start();//播放
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//设置路径
        
        
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom,null);
       
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
        
        DisplayMetrics mDisplayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
        int W = mDisplayMetrics.widthPixels;
        int H = mDisplayMetrics.heightPixels;
        Log.i("ly", "Width = " + W);
        Log.i("ly", "Height = " + H);
        
//        ViewGroup scrollview=(ViewGroup) this.findViewById(R.id.scrollview);
//       //backgroundLayout.setBackground(this.getResources().getDrawable(R.drawable.main_bg));
//        View child=LayoutInflater.from(this).inflate(R.layout.common_test_layout, null);
//        scrollview.addView(child);
     
        final ViewGroup include_test=(ViewGroup) this.findViewById(R.id.include_test);
        handler.postDelayed(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				include_test.setVisibility(View.VISIBLE);
			}
		}, 1000);
        
        Button btn=(Button) this.findViewById(R.id.btn_flag);
        btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent it = new Intent();
				Bundle bundle = new Bundle();
				bundle.putString("content", "test");
				it.putExtras(bundle);
				it.setClass(CommonActivity.this, CommonActivity.class);
				CommonActivity.this.startActivity(it);
			}
		});

		SharedPreferences sp=this.getSharedPreferences("test", MODE_PRIVATE);
		sp.edit().putString("name","ly").commit();

		SharedPreferences sp_read=getSharedPreferences("test",MODE_PRIVATE);
		sp.getString("name", " ");


		System.out.println(Environment.getExternalStorageDirectory().getPath());
		System.out.println(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getPath());
		System.out.println(this.getCacheDir().getPath());
		System.out.println(this.getExternalCacheDir().getPath());


		id4= (ImageView) findViewById(R.id.id4);
		Bitmap bitmap=BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getPath()+File.separator+"" +
				"sample_picture_2015-08-22_14-46-37.jpg");
		if(bitmap==null){
			Log.e("ly","can not decode bitmap");
		}else{
			id4.setImageBitmap(bitmap);
		}

	}
	private void parseIntent() {
		// TODO Auto-generated method stub
		Intent it=getIntent();
		if(it.hasExtra("content")){
			Log.d("ly", "form it:"+it.getStringExtra("content"));
			Log.d("ly", "form bundle:"+it.getExtras().getString("content"));
		}
//		if(it.hasExtra("content")){
//			Log.d("ly", "content");
//		}
			
	}
	public static boolean isValidUrl(String string) {
		Matcher matcher = URL_PATTERN.matcher(string);
		return matcher.matches();
		
	}
	private final static Pattern URL_PATTERN = Pattern
			.compile("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");

	@Override
	protected void onStart() {
		super.onStart();
		Log.i("ly","onstart");
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.i("ly", "onresume");
	}

}

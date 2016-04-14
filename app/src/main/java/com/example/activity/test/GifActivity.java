package com.example.activity.test;

import com.example.androidtest.R;
import com.example.gif.GifView;
import com.example.gif.GifView.GifImageType;

import android.app.Activity;
import android.os.Bundle;




public class GifActivity extends Activity {

	private GifView gf0;
	private GifView gf1;	
	private GifView gf2;
	
	
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		
		setContentView(R.layout.gif);
		
		gf0 = (GifView)findViewById(R.id.gif0);
		gf0.setGifImage(R.drawable.viewdraghelper);
		
		gf1 = (GifView)findViewById(R.id.gif1);
		gf1.setGifImage(R.drawable.animation);
		gf1.setGifImageType(GifImageType.COVER);
		
		gf2 = (GifView)findViewById(R.id.gif2);
//		gf2.setGifImageType(GifImageType.COVER);
		gf2.setGifImage(R.drawable.animator);
		gf2.setShowDimension(300, 300);

	}
	

}

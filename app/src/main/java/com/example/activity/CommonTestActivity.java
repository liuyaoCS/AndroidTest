package com.example.activity;


import com.example.androidtest.R;

import android.annotation.SuppressLint;
import android.app.Activity;

import android.os.Bundle;
import android.webkit.WebView;


public class CommonTestActivity extends Activity {

	private WebView web;
	String path="http://m.chinaso.com/plus/plus_weather_new.html?type=app";
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_common);

		web=(WebView) findViewById(R.id.web);
		web.loadUrl(path);

	}


}

package com.example.activity.view;

import com.example.androidtest.R;
import com.example.view.NavView;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

public class MyViewActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_view);
		
		final NavView navview=(NavView) this.findViewById(R.id.navview);
		new Handler(){}.postDelayed(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				navview.testFunc();
			}}, 1000);
	}
}

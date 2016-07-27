package com.example.activity.view;

import com.example.view.ShaderView;

import android.app.Activity;
import android.os.Bundle;

public class ShaderActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new ShaderView(this));
		
	}
}

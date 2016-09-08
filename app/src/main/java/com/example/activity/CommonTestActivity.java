package com.example.activity;


import com.example.adapter.TestAdapter;
import com.example.androidtest.R;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class CommonTestActivity extends Activity {
	ListView listView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_common_test);

		List<String> datas=new ArrayList<>();
		for(int i=0;i<30;i++){
			datas.add("hello-"+i);
		}
		listView= (ListView) findViewById(R.id.list);
		listView.setAdapter(new TestAdapter(this,datas));

	}


}
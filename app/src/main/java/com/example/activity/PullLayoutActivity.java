package com.example.activity;

import com.example.androidtest.R;
import com.example.view.PullLayout;
import com.example.view.PullLayout.OnRefreshListener;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class PullLayoutActivity extends Activity {
	PullLayout pullLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_layout);
        pullLayout=(PullLayout) this.findViewById(R.id.pull_layout);
        pullLayout.setOnRefreshListener(new OnRefreshListener(){

			@Override
			public void onRefresh() {
				// TODO Auto-generated method stub
				Toast.makeText(PullLayoutActivity.this, "refresh over", Toast.LENGTH_SHORT).show();
			}});
        
    }
}

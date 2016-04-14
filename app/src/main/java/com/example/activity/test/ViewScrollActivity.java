package com.example.activity.test;

import com.example.androidtest.R;
import com.example.view.PullRefreshLayout;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;


public class ViewScrollActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_scroll);
		final PullRefreshLayout swipe=(PullRefreshLayout) this.findViewById(R.id.swipe);
		swipe.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
	        @Override
	        public void onRefresh() {
	        	Toast.makeText(ViewScrollActivity.this, "refresh", Toast.LENGTH_SHORT).show();
	        }
	    });
		
		
		
//		Handler handler=new Handler();
//		handler.postDelayed(new Runnable(){
//
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				swipe.scrollTo(0, 400);
//			}}, 2000);
	}
}

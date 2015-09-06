package com.example.activity;



import com.example.androidtest.R;
import com.example.helper.ExpandCollapseHelper;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class Animation2Activity extends Activity {
	Button btn;
	ImageView pic;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_animation2);
		
		pic=(ImageView) this.findViewById(R.id.pic);
		btn=(Button) this.findViewById(R.id.btn);
		btn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(pic.getVisibility()==View.GONE){
					
					ExpandCollapseHelper.animateExpanding(pic);
				}else{
					ExpandCollapseHelper.animateCollapsing(pic);
				}
			}});
	}
}

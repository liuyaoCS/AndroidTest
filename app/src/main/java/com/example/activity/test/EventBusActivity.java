package com.example.activity.test;




import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import com.example.androidtest.R;
import com.example.fragment.MyFragment;
import com.example.helper.Constants;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;


public class EventBusActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event_bus);
		EventBus.getDefault().register(this);
		
		
		FragmentManager fm=this.getSupportFragmentManager();
		final FragmentTransaction ft=fm.beginTransaction();
		final MyFragment f=new MyFragment();
		ft.add(R.id.container_fragment, f);
		ft.commit();
		
		
		final String event="eventObject";
		
		this.findViewById(R.id.jump).setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				EventBus.getDefault().post(event, Constants.TAG);
			}});
		
	}
	@Subscriber
    private void primitiveParam(int aInt) {
        Toast.makeText(this, "int = " + aInt, Toast.LENGTH_SHORT).show();
    }
}

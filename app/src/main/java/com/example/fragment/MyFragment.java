package com.example.fragment;



import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import com.example.androidtest.R;
import com.example.helper.Constants;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class MyFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		//return super.onCreateView(inflater, container, savedInstanceState);
		View view=inflater.inflate(R.layout.fragment_layout, null);
		EventBus.getDefault().register(this);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		EventBus.getDefault().post(12345);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		EventBus.getDefault().unregister(this);
		super.onDestroy();
	}
	@Subscriber(tag=Constants.TAG)
	public void showReceivedEvent(String event){
		Toast.makeText(getActivity(), "event:"+event, Toast.LENGTH_SHORT).show();
	}
	
}

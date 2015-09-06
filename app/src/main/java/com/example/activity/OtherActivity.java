package com.example.activity;

import java.io.IOException;
import java.io.InputStream;

import com.example.androidtest.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.TextView;
/**
 * 
 * @author ly
 *
 */
public class OtherActivity extends Activity {
	TextView tv=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main58);
		tv=(TextView) this.findViewById(R.id.text_id);
		tv.setText("cpu: "+Integer.valueOf(getMaxCpuFreq())/1024+" mhz");
		Log.v("activityLY", "o  create");
	}

	 public static String getMaxCpuFreq() {
         String result = "";
         ProcessBuilder cmd;
         try {
                 String[] args = { "/system/bin/cat",
                                 "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq" };
                 cmd = new ProcessBuilder(args);
                 Process process = cmd.start();
                 InputStream in = process.getInputStream();
                 byte[] re = new byte[24];
                 while (in.read(re) != -1) {
                         result = result + new String(re);
                 }
                 in.close();
         } catch (IOException ex) {
                 ex.printStackTrace();
                 result = "N/A";
         }
         return result.trim();
	 }
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.v("activityLY", "o start");
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		Log.v("activityLY", "o restart");
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.v("activityLY", "o resume");
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.v("activityLY", "o pause");
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.v("activityLY", "o stop");
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.v("activityLY", "o destroy");
	}
	
	
	
}

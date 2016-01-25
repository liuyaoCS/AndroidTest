package com.example.activity;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.androidtest.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.net.UrlQuerySanitizer;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.ContactsContract;
import android.telephony.PhoneNumberUtils;
import android.text.Html;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class CommonActivity extends Activity {
	Handler handler;
	ImageView id4;
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_common);

		runPingIPprocess("111.203.244.4");

	}
	public void runPingIPprocess(final String ipString)
	{
		final Thread pingThread = new Thread()
		{
			@Override
			public void run()
			{
				try
				{
					Log.i("Light", "ip address" + ipString);
					// ping ip地址
					Process p = Runtime.getRuntime().exec(
							"ping -c 1 -w 5 " + ipString);
					int status = p.waitFor();
					if (status == 0)
					{
						Log.i("Light", "ping ip");

					} else
					{
						Log.i("Light", "ping ip error");
					}

				} catch (Exception e)
				{

					Log.e("Light", e.getLocalizedMessage());
				} finally
				{
				}
			}
		};
		pingThread.start();
	}
	private void parseIntent() {
		// TODO Auto-generated method stub
		Intent it=getIntent();
		if(it.hasExtra("content")){
			Log.d("ly", "form it:"+it.getStringExtra("content"));
			Log.d("ly", "form bundle:"+it.getExtras().getString("content"));
		}
//		if(it.hasExtra("content")){
//			Log.d("ly", "content");
//		}
			
	}
	public static boolean isValidUrl(String string) {
		Matcher matcher = URL_PATTERN.matcher(string);
		return matcher.matches();
		
	}
	private final static Pattern URL_PATTERN = Pattern
			.compile("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");

	@Override
	protected void onStart() {
		super.onStart();
		Log.i("ly","onstart");
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.i("ly", "onresume");
	}

}

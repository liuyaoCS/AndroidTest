package com.example.activity;


import com.example.androidtest.R;
import com.example.helper.MyWebSetting;


import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.GeolocationPermissions;
import android.webkit.JavascriptInterface;
import android.webkit.WebBackForwardList;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;



public class CommonTestActivity extends Activity {

	WebView web;

	@Override
	@JavascriptInterface
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_ptr_webview);

		web= (WebView) findViewById(R.id.web);

		web.setWebViewClient(new MyWebViewClient());
		web.setWebChromeClient(new MyWebChromeClient());

		WebSettings myWebSettings = web.getSettings();

		MyWebSetting unifySetting = new MyWebSetting(this, myWebSettings);
		unifySetting.setDatabasePath();
		unifySetting.setSaveMode();
		unifySetting.setZoomMode();

		unifySetting.setUseWideViewPort(true);
		unifySetting.setNewUserAgentString();

		web.loadUrl("http://192.168.66.20/geo/test.html");

		Button size=(Button)findViewById(R.id.btn);
		size.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(CommonTestActivity.this,""+web.getContentHeight()+"scale:"+web.getScale(),Toast.LENGTH_LONG).show();
			}
		});


	}
	private class MyWebViewClient extends WebViewClient {
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			//myWebView.loadUrl(url);
			return false;
		}

		@Override
		public void onPageStarted(WebView view, String url, Bitmap favicon) {
			super.onPageStarted(view, url, favicon);
			WebBackForwardList list = view.copyBackForwardList();
			Integer backi = null;
			for(int i=0;i<list.getSize();i++){
				String url1 = list.getItemAtIndex(i).getUrl();
				Log.i("ly","onPageStarted url->"+url1);
				//select the appropriate index and set to backi
			}
			Log.i("ly","current url->"+url+" current index->"+list.getCurrentIndex()+" size->"+list.getSize());

		}


	}
	private class MyWebChromeClient extends WebChromeClient {
		@Override
		public void onProgressChanged(WebView view, int newProgress) {

			super.onProgressChanged(view, newProgress);
		}

		@Override
		public void onGeolocationPermissionsShowPrompt(String origin,
													   GeolocationPermissions.Callback callback) {
			callback.invoke(origin, true, false);
			super.onGeolocationPermissionsShowPrompt(origin, callback);
		}
	}

}
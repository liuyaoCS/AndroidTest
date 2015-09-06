package com.example.activity;

import java.text.RuleBasedCollator;

import com.example.androidtest.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

public class JSActivity extends Activity {
	private Button btn;
	private WebView mWebView;
	private Activity mActivity = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_js);
		btn = (Button)findViewById(R.id.btn);
		btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mWebView.loadUrl("javascript:alert(jsObj.HtmlcallJava())");
				Toast.makeText(JSActivity.this, "本地Btn", Toast.LENGTH_SHORT).show();
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						mWebView.loadUrl("javascript: showFromHtml()");
					}
				});
			}
		});
		
		mActivity = this;
		
		showWebView();
	}
	
	@SuppressLint("SetJavaScriptEnabled")
	private void showWebView(){		// webView与js交互代码
			mWebView = (WebView)findViewById(R.id.webView);
			
			mWebView.requestFocus();
			
			mWebView.setWebChromeClient(new WebChromeClient(){
				@Override
				public void onProgressChanged(WebView view, int progress){
					JSActivity.this.setTitle("Loading...");
					JSActivity.this.setProgress(progress);
					
					if(progress >= 80) {
						JSActivity.this.setTitle("JsAndroid Test");
					}
				}
			});
			
			mWebView.setOnKeyListener(new View.OnKeyListener() {		// webview can go back
				@Override
				public boolean onKey(View v, int keyCode, KeyEvent event) {
					if(keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()) {
						mWebView.goBack();
						return true;
					}
					return false;
				}
			});
			
			WebSettings webSettings = mWebView.getSettings();
			webSettings.setJavaScriptEnabled(true);
			webSettings.setDefaultTextEncodingName("utf-8");

			mWebView.addJavascriptInterface(getHtmlObject(), "jsObj");
			mWebView.loadUrl("http://www.zhangxi.me/chinaso/refresh.html");
	}

	private Object getHtmlObject(){
		Object insertObj = new Object(){
			@JavascriptInterface
			public String HtmlcallJava(){
				return "Html call Java";
			}
			
			public String HtmlcallJava2(final String param){
				return "Html call Java : " + param;
			}
			
			
			
			
			public void JavacallHtml(){
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						mWebView.loadUrl("javascript: showFromHtml()");
						Toast.makeText(JSActivity.this, "clickBtn", Toast.LENGTH_SHORT).show();
					}
				});
			}
			
			public void JavacallHtml2(){
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						mWebView.loadUrl("javascript: showFromHtml2('IT-homer blog')");
						Toast.makeText(JSActivity.this, "clickBtn2", Toast.LENGTH_SHORT).show();
					}
				});
			}
		};
		
		return insertObj;
	}

	
}

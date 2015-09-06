package com.example.activity;


import com.example.androidtest.R;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.sso.EmailHandler;
import com.umeng.socialize.sso.QZoneSsoHandler;
import com.umeng.socialize.sso.SmsHandler;
import com.umeng.socialize.sso.UMQQSsoHandler;
import com.umeng.socialize.weixin.controller.UMWXHandler;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class UmShareActivity extends Activity {
	View tv;
	UMSocialService mController=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_um_share);
		init();
		tv= this.findViewById(R.id.share);
		tv.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mController.openShare(UmShareActivity.this, false);
			}});
	}
	private void init(){
		mController = UMServiceFactory.getUMSocialService("com.umeng.share");
		// 设置分享内容
		mController.setShareContent("友盟社会化组件（SDK）让移动应用快速整合社交分享功能，http://www.umeng.com/social");
		// 设置分享图片, 参数2为图片的url地址
		mController.setShareMedia(new UMImage(this, "http://www.baidu.com/img/bdlogo.png"));
		                                      
		 addWXPlatform();
		 addSMS();
		 addEmail();
		 addQQQZonePlatform();
	}
	private void addWXPlatform() {
        // 注意：在微信授权的时候，必须传递appSecret
        // wx967daebe835fbeac是你在微信开发平台注册应用的AppID, 这里需要替换成你注册的AppID
        String appId = "wx8841411cd87e66cb";
        String appSecret = "81430fc6fc85f05c0ffd2c744087f5a1";
        // 添加微信平台
        UMWXHandler wxHandler = new UMWXHandler(UmShareActivity.this, appId, appSecret);
        wxHandler.addToSocialSDK();

        // 支持微信朋友圈
        UMWXHandler wxCircleHandler = new UMWXHandler(UmShareActivity.this, appId, appSecret);
        wxCircleHandler.setToCircle(true);
        wxCircleHandler.addToSocialSDK();
    }
	private void addSMS() {
	       // 添加短信
	       SmsHandler smsHandler = new SmsHandler();
	       smsHandler.addToSocialSDK();
	   }
	private void addEmail() {
	       // 添加email
	       EmailHandler emailHandler = new EmailHandler();
	       emailHandler.addToSocialSDK();
	   }
	private void addQQQZonePlatform() {
        String appId = "1102299443";
        String appKey = "QenBhx6zFRtY4M9h";
        // 添加QQ支持, 并且设置QQ分享内容的target url
        UMQQSsoHandler qqSsoHandler = new UMQQSsoHandler(UmShareActivity.this,
                appId, appKey);

        qqSsoHandler.addToSocialSDK();

        // 添加QZone平台
        QZoneSsoHandler qZoneSsoHandler = new QZoneSsoHandler(UmShareActivity.this, appId, appKey);
        qZoneSsoHandler.addToSocialSDK();
    }
}

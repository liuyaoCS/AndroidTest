package com.example.activity.view;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.androidtest.R;

public class VideoViewActivity extends Activity {
    VideoView videoView;
    String path="rtsp://218.204.223.237:554/live/1/0547424F573B085C/gsfp90ef4k0a6iap.sdp";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view);

        videoView= (VideoView) findViewById(R.id.video);

        Uri uri = Uri.parse(path);


        videoView.setMediaController(new MediaController(this));

        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.start();
    }
}

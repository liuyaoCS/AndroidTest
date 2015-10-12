package com.example.experience;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.*;
import android.view.SurfaceHolder.Callback;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidtest.R;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

//@SuppressLint(SimpleDateFormat)
public class MovieRecorderActivity2 extends Activity implements Callback {
    private SurfaceView mSurfaceview;
    private Button mBtnStartStop;// 开始停止录制按键
    private boolean mStartedFlg = false;
    private MediaRecorder mRecorder;// 录制视频的类
    private SurfaceHolder mSurfaceHolder;// 显示视频
    private Camera camera;
    private TextView timeView;// 在屏幕顶部显示录制时间
    private int time = 0;

    //@SuppressLint(HandlerLeak)
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 1:// 开始录制
                    timeView.setText(time +  "s");
                    break;
                case 2:// 录制8s结束
                    mRecorder.stop();
                    mRecorder.reset(); // You can reuse the object by
                    mBtnStartStop.setText("Start ");
                    mStartedFlg = false;
                    time = 0;
                    timeView.setText(time +  "s");
                    break;
                case 3:// 体检结束
                    time = 0;
                    timeView.setText(time +  "s");
                    break;

            }
        }

        ;
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);// 设置全屏
        getWindow().setFormat(PixelFormat.TRANSLUCENT); // 选择支持半透明模式,在有surfaceview的activity中使用。
        setContentView(R.layout.activity_movie_recorder_activity2);// 加载布局

        timeView = (TextView) findViewById(R.id.time);
        mSurfaceview = (SurfaceView) findViewById(R.id.sView);
        mBtnStartStop = (Button) findViewById(R.id.btn);
        mBtnStartStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (!mStartedFlg) {
                    // 开始
                    if (mRecorder == null) {
                        mRecorder = new MediaRecorder(); // 创建mediarecorder的对象
                    }
                    try {
                        camera.unlock();
                        mRecorder.setCamera(camera);
                        mRecorder
                                .setAudioSource(MediaRecorder.AudioSource.CAMCORDER);// 这两项需要放在setOutputFormat之前
                        mRecorder
                                .setVideoSource(MediaRecorder.VideoSource.CAMERA);// 设置录制视频源为Camera(相机)
                        mRecorder.setOrientationHint(90);
                        // Set output file format
                        mRecorder
                                .setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);// 设置录制完成后视频的封装格式THREE_GPP为3gp.MPEG_4为mp4

                        // 这两项需要放在setOutputFormat之后
                        mRecorder
                                .setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                        mRecorder
                                .setVideoEncoder(MediaRecorder.VideoEncoder.MPEG_4_SP);// 设置录制的视频编码h263

                        mRecorder.setVideoSize(800, 480);// 设置视频录制的分辨率。必须放在设置编码和格式的后面，否则报错
                        mRecorder.setVideoFrameRate(30);// 设置录制的视频帧率。必须放在设置编码和格式的后面，否则报错

                        mRecorder.setMaxDuration(8000);// 设置最大的录制时间
                        mRecorder.setPreviewDisplay(mSurfaceHolder.getSurface());

                        // Set output file path
                        String path = getSDPath();
                        if (path != null) {
                            File dir = new File(path +File.separator+"chinaso");
                            if (!dir.exists()) {
                                dir.mkdir();
                            }
                            path = dir + File.separator+ getDate() + ".mp4";
                            Toast.makeText(MovieRecorderActivity2.this, path,
                                    Toast.LENGTH_LONG).show();
                            mRecorder.setOutputFile(path);

                            mRecorder.prepare();// 准备录制

                            mRecorder.start(); // 开始录制

                            new Thread(new Runnable() {

                                @Override
                                public void run() {
                                    while (time < 8) {
                                        try {
                                            time++;
                                            Thread.sleep(1000);
                                            handler.sendEmptyMessage(1);
                                        } catch (InterruptedException e) {
                                            // TODO Auto-generated catch block
                                            e.printStackTrace();
                                        }

                                    }
                                    handler.sendEmptyMessage(2);

                                }
                            }).start();

                            mStartedFlg = true;
                            mBtnStartStop.setText("Stop");

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    // stop
                    if (mStartedFlg) {
                        try {
                            mRecorder.stop();
                            mRecorder.reset(); // You can reuse the object by
                            // going back to
                            // setAudioSource() step
                            mBtnStartStop.setText("Start");
                            handler.sendEmptyMessageDelayed(3, 1000);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    mStartedFlg = false; // Set button status flag
                }
            }

        });

        SurfaceHolder holder = mSurfaceview.getHolder();// 取得holder

        holder.addCallback(this); // holder加入回调接口

        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);// setType必须设置，要不出错.

    }

    /**
     * 使用时间对录像起名
     *
     * @return
     */
    public static String getDate() {
        Calendar ca = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        String date = sdf.format(ca.getTimeInMillis());
        return date;
    }

    /**
     * 获取SD path
     *
     * @return
     */
    public String getSDPath() {
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED); // 判断sd卡是否存在
        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory();// 获取跟目录
            // Toast.makeText(this,sdDir.toString(),Toast.LENGTH_LONG).show();
            return sdDir.toString();
        } else {
            Toast.makeText(this, "没有SD卡", Toast.LENGTH_LONG).show();
        }

        return null;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
        camera = Camera.open(); // 获取Camera实例
        try {
            camera.setPreviewDisplay(holder);
            mSurfaceview.setLayoutParams(new LinearLayout.LayoutParams(width,
                    height));
        } catch (Exception e) {
            // 如果出现异常，则释放Camera对象
            camera.release();
        }
        camera.setDisplayOrientation(90);// 设置预览视频时时竖屏
        // 启动预览功能
        camera.startPreview();
        // 将holder，这个holder为开始在onCreate里面取得的holder，将它赋给mSurfaceHolder
        mSurfaceHolder = holder;

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        // 将holder，这个holder为开始在onCreate里面取得的holder，将它赋给mSurfaceHolder
        mSurfaceHolder = holder;
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // TODO Auto-generated method stub
        // surfaceDestroyed的时候同时对象设置为null
        mSurfaceview = null;
        mSurfaceHolder = null;
        if (mRecorder != null) {
            mRecorder.release(); // Now the object cannot be reused
            mRecorder = null;
        }
        if (camera != null) {
            camera.release();
            camera = null;
        }
    }
}
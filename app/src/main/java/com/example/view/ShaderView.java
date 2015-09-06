package com.example.view;
import com.example.androidtest.R;

import android.content.Context;  
import android.graphics.*;  
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;  
import android.graphics.drawable.ShapeDrawable;  
import android.graphics.drawable.shapes.OvalShape;  
import android.view.*;  
public class ShaderView extends SurfaceView implements SurfaceHolder.Callback,Runnable{  
    //声明渐变的颜色数组  
    private int[] color =   
        new int[]{Color.GREEN,Color.GRAY,Color.MAGENTA,Color.RED,Color.WHITE};  
    private boolean loop = false;  
    private SurfaceHolder surfaceHolder;  
      
    private Bitmap bitPic = null;  
      
    int bitPicWidth = 0;  
    int bitPicHeight = 0;  
      
    //声明一个图片渲染  
    BitmapShader bitmapShader = null;  
    //声明一个线性渐变  
    LinearGradient linearGradient = null;  
    //声明一个环形渐变  
    RadialGradient radialGradient = null;  
    //声明一个扫描渐变  
    //-围绕一个中心点扫描渐变就像电影里那种雷达扫描  
    SweepGradient sweepGradient = null;  
    //声明一个组合渲染  
    ComposeShader composeShader = null;  
    //定义画笔  
    Paint paint = null;  
    //利用这个类也可以实现绘制图像的功能  
    ShapeDrawable shapeDrawable = null;  
      
    public ShaderView(Context context) {  
        super(context);  
        surfaceHolder = this.getHolder();  
        //增加回调  
        surfaceHolder.addCallback(this);  
        loop = true;  
        paint = new Paint();  
        //获取图像资源  
        bitPic =   
            ((BitmapDrawable)this.getResources().getDrawable(R.drawable.home_logo))  
            .getBitmap();  
        //将图片的长和高的值赋给变量  
        bitPicWidth = bitPic.getWidth();  
        bitPicHeight = bitPic.getHeight();  
        /* 
         * ~~~BitmapShader(Bitmap,TileMode,TileMode)~~~ 
         */  
        bitmapShader = new BitmapShader(bitPic, TileMode.REPEAT, TileMode.MIRROR);  
        /* 
         * ~~~LinearGradient(x0,y0,x1,y1,int[Color],float[],TileMode)~~~ 
         */  
        linearGradient = new LinearGradient(0,0,100,100,color,null,TileMode.REPEAT);  
        /* 
         * ~~~RadialGradient~~~ 
         */  
        radialGradient = new RadialGradient(160,240,66,color,null,TileMode.MIRROR);  
        /* 
         * ~~~SweepGradient~~~ 
         */  
        sweepGradient = new SweepGradient(100,350,color,null);  
        //~~~ComposeShader（shaderA,shaderB,Mode）~~~  
        //组合线性和环形两种渐变,当然其他的也可以的  
        composeShader   
            = new ComposeShader(linearGradient,radialGradient,PorterDuff.Mode.DARKEN);  
    }  
    @Override  
    public void run() {  
        while(loop) {  
            draw();  
            try {  
                Thread.sleep(100);  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
    @Override  
    public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {  
    }  
    @Override  
    public void surfaceCreated(SurfaceHolder arg0) {  
        new Thread(this).start();  
    }  
    @Override  
    public void surfaceDestroyed(SurfaceHolder arg0) {  
        loop = false;  
    }  
      
    private void draw() {  
        Canvas canvas = surfaceHolder.lockCanvas();  
        /* 
         * ~~~BitmapShader 
         */  
        //构造形状为椭圆的shapeDrawable对象  
        shapeDrawable = new ShapeDrawable(new OvalShape());  
        //设置显示的图片  
        shapeDrawable.getPaint().setShader(bitmapShader);  
        //设置显示的长和高  
        shapeDrawable.setBounds(0, 0, bitPicWidth, bitPicHeight);  
        //绘制图像  
        shapeDrawable.draw(canvas);  
        //~~~LinearGradient~~~  
        //设置画笔的渲染类型  
        paint.setShader(linearGradient);  
        canvas.drawRect(0, bitPicHeight, 320, 150, paint);  
        //~~~RadialGradient~~~  
        paint.setShader(radialGradient);  
        canvas.drawCircle(160, 240, 66, paint);  
        //~~~SweepGradient  
        paint.setShader(sweepGradient);  
        canvas.drawCircle(100, 350, 66, paint);  
        //~~~ComposeShader~~~  
        paint.setShader(composeShader);  
        canvas.drawRect(bitPicWidth, 320, 320, 480, paint);  
        surfaceHolder.unlockCanvasAndPost(canvas);  
    }  
}  
package com.example.view;

import com.example.androidtest.R;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;


/**
 * Titanic
 * romainpiel
 * 13/03/2014
 */
public class WaveTextView extends TextView {

    public interface AnimationSetupCallback {
        public void onSetupAnimation(WaveTextView titanicTextView);
    }

    // callback fired at first onSizeChanged
    private AnimationSetupCallback animationSetupCallback;
    // wave shader coordinates
    private float maskX, maskY;
    // if true, the shader will display the wave
    private boolean sinking;
    // true after the first onSizeChanged
    private boolean setUp;

    // shader containing a repeated wave
    private BitmapShader shader;
    // shader matrix
    private Matrix shaderMatrix;
    // wave drawable
    private Drawable wave;


    public WaveTextView(Context context) {
        super(context);
        init();
    }

    public WaveTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public WaveTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        shaderMatrix = new Matrix();
    }

    public AnimationSetupCallback getAnimationSetupCallback() {
        return animationSetupCallback;
    }

    public void setAnimationSetupCallback(AnimationSetupCallback animationSetupCallback) {
        this.animationSetupCallback = animationSetupCallback;
    }

    public float getMaskX() {
        return maskX;
    }

    public void setMaskX(float maskX) {
        this.maskX = maskX;
        invalidate();
    }

    public float getMaskY() {
        return maskY;
    }

    public void setMaskY(float maskY) {
        this.maskY = maskY;
        invalidate();
    }

    public boolean isSinking() {
        return sinking;
    }

    public void setSinking(boolean sinking) {
        this.sinking = sinking;
    }

    public boolean isSetUp() {
        return setUp;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.i("ly", "onSizeChanged w="+w+" h="+h);
        createShader();

        if (!setUp) {
            setUp = true;
            if (animationSetupCallback != null) {
                animationSetupCallback.onSetupAnimation(WaveTextView.this);
            }
        }
    }

    /**
     * Create the shader
     * draw the wave with current color for a background
     * repeat the bitmap horizontally, and clamp colors vertically
     */
    private void createShader() {

        if (wave == null) {
            wave = getResources().getDrawable(R.drawable.imitate_titanic_wave);
        }

//        int waveW = wave.getIntrinsicWidth();
//        int waveH = wave.getIntrinsicHeight();
//
//        Bitmap b = Bitmap.createBitmap(waveW, waveH, Bitmap.Config.ARGB_8888);
        
//      Canvas c = new Canvas(b);        
//      c.drawColor(getCurrentTextColor());
//      wave.setBounds(0, 0, waveW, waveH);
//      wave.draw(c);

        shader = new BitmapShader(((BitmapDrawable)wave).getBitmap(), Shader.TileMode.REPEAT, Shader.TileMode.CLAMP);
        getPaint().setShader(shader);
        
        
        

        

    }

    @Override
    protected void onDraw(Canvas canvas) {

        // modify text paint shader according to sinking state
    	//canvas.drawColor(getCurrentTextColor());
    	//getPaint().setColor(getCurrentTextColor());
        if (sinking && shader != null) {

            // first call after sinking, assign it to our paint
            if (getPaint().getShader() == null) {
                getPaint().setShader(shader);
            }

            // translate shader accordingly to maskX maskY positions
            // maskY is affected by the offset to vertically center the wave
            shaderMatrix.setTranslate(maskX, maskY);
            Log.i("ly", "onDraw maskX="+maskX+" maskY="+maskY);

            // assign matrix to invalidate the shader
            shader.setLocalMatrix(shaderMatrix);
        } else {
            getPaint().setShader(null);
        }
        Log.i("ly", "canvas"+canvas.toString());
        super.onDraw(canvas);
    }
}

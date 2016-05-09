package com.example.activity.test;

import com.example.androidtest.R;
import com.example.view.Wave;
import com.example.view.WaveTextView;

import android.app.Activity;
import android.os.Bundle;




public class WaveActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wave_main);

        WaveTextView tv = (WaveTextView) findViewById(R.id.my_text_view);

        // set fancy typeface
        //tv.setTypeface(Typefaces.get(this, "Satisfy-Regular.ttf"));

        // start animation
        new Wave().start(tv);
    }

}

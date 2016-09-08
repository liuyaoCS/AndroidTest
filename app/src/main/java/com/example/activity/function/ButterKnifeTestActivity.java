package com.example.activity.function;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidtest.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ButterKnifeTestActivity extends Activity {

    @Bind(R.id.btn)
    Button btn;
    @Bind(R.id.tv)
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_butter_knife_test);
        ButterKnife.bind(this);
        tv.setText("this is from butterknife inject tv");
    }

    @OnClick(R.id.btn)
    public void onClick() {
        Toast.makeText(this,"this is from butterknife inject btn",Toast.LENGTH_SHORT).show();
        btn.setText("show my text");
    }
}

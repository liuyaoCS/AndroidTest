package com.example.activity;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.androidtest.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class CommonTestActivity extends Activity {
    ListView listView;
    @Bind(R.id.tv)
    TextView tv;
    @Bind(R.id.btn)
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_test);

        ButterKnife.bind(this);

    }


    @OnClick(R.id.btn)
    public void onClick() {
        tv.setVisibility(View.GONE);
    }
}
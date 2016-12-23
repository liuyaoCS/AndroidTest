package com.example.activity;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.adapter.TestAdapter;
import com.example.androidtest.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class CommonTestActivity extends Activity {

    @Bind(R.id.list)
    ListView listView;
    @Bind(R.id.btn)
    Button btn;
    private final List<String> datas = new ArrayList<>();
    private final TestAdapter testAdapter = new TestAdapter(this, datas);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_test);

        ButterKnife.bind(this);

        datas.add("ly");
        datas.add("zs");
        listView.setAdapter(testAdapter);

    }


    @OnClick(R.id.btn)
    public void onClick() {
        datas.add("zzs");
        testAdapter.notifyDataSetChanged();
    }
}
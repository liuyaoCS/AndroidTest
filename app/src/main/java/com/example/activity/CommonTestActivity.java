package com.example.activity;


import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.adapter.TestAdapter;
import com.example.androidtest.R;
import com.example.view.MyTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.androidtest.R.id.tv;


public class CommonTestActivity extends Activity{

    Button btn;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_test);
        init();

    }
    private void init() {
        btn = (Button) findViewById(R.id.btn);
        list= (ListView) findViewById(R.id.list);
        String[] strs=new String[30];
        for(int i=0;i<30;i++)strs[i]=i+"";
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,strs);
        list.setAdapter(adapter);
        final TextView view=new TextView(this);
        view.setText("i am header");
       // view.setVisibility(View.GONE);

        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                list.addHeaderView(view);
                //view.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                       list.removeHeaderView(view);
                    }
                },1000);
            }
        });
    }


}
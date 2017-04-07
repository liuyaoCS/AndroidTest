package com.example.activity.view;


import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.androidtest.R;


public class ArrayListViewActivity extends Activity{

    Button btn;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arraylistview_test);
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
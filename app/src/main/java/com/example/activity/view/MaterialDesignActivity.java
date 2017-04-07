package com.example.activity.view;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.androidtest.R;

public class MaterialDesignActivity extends AppCompatActivity {
    RelativeLayout root;
    FloatingActionButton btn;
    TextInputLayout textLayout;
    FloatingActionButton btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_design);
        root= (RelativeLayout) findViewById(R.id.activity_material_design);
        btn= (FloatingActionButton) findViewById(R.id.fab_search);
        textLayout= (TextInputLayout) findViewById(R.id.text_layout);
        btn2=(FloatingActionButton) findViewById(R.id.fab);

        testSnackBar();
        testFloatingActionButton();
        testCoordinatorLayout();
    }
    private void testSnackBar(){
        Snackbar.make(root, "Hello SnackBar!", Snackbar.LENGTH_LONG)
                .setAction("Undo", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Perform anything for the action selected
                        Log.i("ly","clicked");
                    }
                })
                .show();
    }
    private void testFloatingActionButton(){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textLayout.setError("用户名错误！");
            }
        });
    }
    private void testCoordinatorLayout(){
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,"appbarlayout",Snackbar.LENGTH_LONG)
                        .setAction("enter", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //这里的单击事件代表点击消除Action后的响应事件
                                Intent it=new Intent(MaterialDesignActivity.this,AppBarLayoutActivity.class);
                                startActivity(it);
                            }
                        })
                        .show();
            }
        });
    }
}

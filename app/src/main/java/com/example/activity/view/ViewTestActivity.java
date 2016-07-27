package com.example.activity.view;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.example.androidtest.R;

public class ViewTestActivity extends Activity {
    View menuItemsView;
    PopupWindow mPopupWindow;
    Button button,dismiss;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_test);

         /* 写在单独的布局文件，再inflater*/
        menuItemsView = LayoutInflater.from(this).inflate(R.layout.popview_layout, null);
        /* 构建*/
        mPopupWindow = new PopupWindow(menuItemsView, 280, LinearLayout.LayoutParams.WRAP_CONTENT);




        /* 点击屏幕其他地方撤销显示*/
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(0));
        mPopupWindow.setOutsideTouchable(true);
//        mPopupWindow.setTouchInterceptor(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                // TODO Auto-generated method stub
//                if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
//                    mPopupWindow.dismiss();
//                    return true;
//                }
//
//                return false;
//            }
//        });

         /* 设置是否可阻塞，默认是不阻塞的，程序还可以响应其他view事件；设置为true不会传递touch事件给点击的view了，为阻塞状态。此处设置的目的是防止mPopupWindow和控制mPopupWindow出现的button冲突*/
        mPopupWindow.setFocusable(true);

        button= (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.showAsDropDown(button);
            }
        });
        dismiss= (Button) findViewById(R.id.dismiss);
        dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
            }
        });
    }


}

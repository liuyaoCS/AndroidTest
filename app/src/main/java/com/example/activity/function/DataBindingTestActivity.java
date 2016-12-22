package com.example.activity.function;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.example.androidtest.R;
import com.example.androidtest.databinding.ActivityDataBindingTestBinding;
import com.example.databinding.User;

public class DataBindingTestActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_data_binding_test);
        ActivityDataBindingTestBinding dataBinding=DataBindingUtil.setContentView(this,R.layout.activity_data_binding_test);
        dataBinding.setUser(new User("liu","yao"));

    }
}

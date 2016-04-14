package com.example.activity.experience;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.androidtest.R;

public class FragmentTestActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_test);

        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=null;

        Fragment fragment=new MyFragment1();
        Fragment fragment2=new MyFragment2();


        ft=fm.beginTransaction();
        ft.add(R.id.container, fragment);
        ft.commit();

        ft=fm.beginTransaction();
        ft.replace(R.id.container, fragment2);
        ft.commit();

        ft=fm.beginTransaction();
        ft.hide(fragment2);
        ft.commit();

    }
}

package com.example.activity.support;


import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.adapter.MViewPagerAdapter;
import com.example.adapter.RecyclerViewAdapter;
import com.example.androidtest.R;
import com.example.entity.newsList.ListItem;
import com.example.entity.newsList.NewsAll;
import com.example.net.NetworkService;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CoordinatorLayoutActivity extends AppCompatActivity {
    ViewPager viewpager;
    PagerAdapter pAdapter;
    List<View> views;
    private List<ListItem> mDatas;
    RecyclerViewAdapter rAdapter;
    RecyclerView view1,view2,view3;

    Toolbar toolbar;
    TabLayout tab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_layout);
        viewpager= (ViewPager) findViewById(R.id.viewpager);
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        tab= (TabLayout) findViewById(R.id.tabs);

        setSupportActionBar(toolbar);


        rAdapter = new RecyclerViewAdapter(CoordinatorLayoutActivity.this, mDatas);

        view1=new RecyclerView(CoordinatorLayoutActivity.this);
        view1.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        view1.setItemAnimator(new DefaultItemAnimator());//设置动画

        view2=new RecyclerView(CoordinatorLayoutActivity.this);
        view2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        view2.setItemAnimator(new DefaultItemAnimator());//设置动画

        view3=new RecyclerView(CoordinatorLayoutActivity.this);
        view3.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        view3.setItemAnimator(new DefaultItemAnimator());//设置动画

        views=new ArrayList<View>();
        views.add(view1);
        views.add(view2);
        views.add(view3);



        loadNewsList();

    }

    private void loadNewsList() {
        Call<NewsAll> response= NetworkService.getInstance().fetchList("news_all",0);
        response.enqueue(new Callback<NewsAll>() {
            @Override
            public void onResponse(Call<NewsAll> call, Response<NewsAll> response) {
                Log.e("ly","begin parse");
                NewsAll newsAll=response.body();
                mDatas=newsAll.getList();

                rAdapter = new RecyclerViewAdapter(CoordinatorLayoutActivity.this, mDatas);
                view1.setAdapter(rAdapter);
                view2.setAdapter(rAdapter);
                view3.setAdapter(rAdapter);

                pAdapter =new MViewPagerAdapter(views);
                viewpager.setAdapter(pAdapter);

                //tab.setupWithViewPager(viewpager);
            }

            @Override
            public void onFailure(Call<NewsAll> call, Throwable t) {
                Log.e("ly",t.getMessage());
            }
        });
    }
}

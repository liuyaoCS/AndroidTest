package com.example.activity.view;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.adapter.RecyclerViewAdapter;
import com.example.androidtest.R;
import com.example.entity.newsList.ListItem;
import com.example.entity.newsList.NewsAll;
import com.example.net.NetWorkService;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecyclerViewActivity extends AppCompatActivity {
    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private RecyclerViewAdapter mAdapter;
    private List<ListItem> mDatas;
    private int mCurrentPage=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        ButterKnife.bind(this);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Call<NewsAll> response= NetWorkService.getInstance().fetchList("news_all",mCurrentPage++);
                        response.enqueue(new Callback<NewsAll>() {
                            @Override
                            public void onResponse(Call<NewsAll> call, Response<NewsAll> response) {
                                Log.e("ly","begin parse");
                                NewsAll newsAll=response.body();
                                mDatas.addAll(newsAll.getList());
                                mAdapter.notifyDataSetChanged();

                                mSwipeRefreshLayout.setRefreshing(false);
                                Toast.makeText(RecyclerViewActivity.this, "更新了数据...", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(Call<NewsAll> call, Throwable t) {
                                Log.e("ly",t.getMessage());
                            }
                        });

                    }
                }, 500);
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());//设置动画
        loadNewsList();
    }
    private void loadNewsList() {
        Call<NewsAll> response= NetWorkService.getInstance().fetchList("news_all",0);
        response.enqueue(new Callback<NewsAll>() {
            @Override
            public void onResponse(Call<NewsAll> call, Response<NewsAll> response) {
                Log.e("ly","begin parse");
                NewsAll newsAll=response.body();
                mDatas=newsAll.getList();

                mAdapter = new RecyclerViewAdapter(RecyclerViewActivity.this, mDatas);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<NewsAll> call, Throwable t) {
                Log.e("ly",t.getMessage());
            }
        });
    }

}

package com.example.activity.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.adapter.RecyclerViewAdapter;
import com.example.androidtest.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RecyclerViewActivity extends AppCompatActivity {
    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerView;

    private List<String> mDatas;
    private RecyclerViewAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        ButterKnife.bind(this);
        mDatas = new ArrayList<String>();
        for (int i = 'A'; i <= 'z'; i++) {
            mDatas.add("" + (char) i);

        }
        mAdapter = new RecyclerViewAdapter(this, mDatas);
        mAdapter.setmOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                Toast.makeText(RecyclerViewActivity.this,position+"========Click:",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClickListener(View view, int position) {
                Toast.makeText(RecyclerViewActivity.this,position+"+++++++++LongClick:",Toast.LENGTH_LONG).show();

            }
        });


        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());//设置动画
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_recyclerview,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        switch (id){
            case R.id.add :
                mAdapter.add(1);

                break;
            case R.id.delete :
                mAdapter.delete(1);

                break;
            case R.id.gridview :
                mRecyclerView.setLayoutManager(new GridLayoutManager(this,3));
                break;

            case R.id.listview :
                mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.androidtest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/5 0005.
 */
public class TestAdapter extends BaseAdapter{
    Context mContext;
    List<String> mDatasets;
    public TestAdapter(Context context, List<String> datasets){
        mContext=context;
        mDatasets=datasets;
    }

    @Override
    public int getCount() {
        return mDatasets.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatasets.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh=null;
        if(convertView==null){
            vh=new ViewHolder();
            convertView=LayoutInflater.from(mContext).inflate(R.layout.item_adapter_layout,null);
            vh.tv= (TextView) convertView.findViewById(R.id.tv);
            convertView.setTag(vh);
        }else{
            vh= (ViewHolder) convertView.getTag();
        }
        vh.tv.setText((String)getItem(position));
        return convertView;
    }
    class ViewHolder{
        public TextView tv;
    }

}

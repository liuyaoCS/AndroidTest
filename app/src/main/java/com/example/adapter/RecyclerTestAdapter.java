package com.example.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.androidtest.R;

import java.util.List;

/**
 * Created by liuyao on 2016/12/23 0023.
 */

public class RecyclerTestAdapter extends RecyclerView.Adapter<MViewHolder>{
    private List<String> mDatas;
    private LayoutInflater mInflater;
    public RecyclerTestAdapter(Context context,List<String> datas) {
        mDatas=datas;
        mInflater=LayoutInflater.from(context);
    }

    @Override
    public MViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=mInflater.inflate(R.layout.recyclerview_item,parent,false);
        return new MViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(MViewHolder holder, int position) {
        holder.tv.setText(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }


}
class MViewHolder extends RecyclerView.ViewHolder {
    TextView tv;

    public MViewHolder(View itemView) {
        super(itemView);
        tv = (TextView) itemView.findViewById(R.id.item_recyclerview);
    }
}

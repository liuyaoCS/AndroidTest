package com.example.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidtest.R;
import com.example.entity.newsList.ListItem;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by baochen on 2016/1/13.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<MyViewHolder>{
    protected List<ListItem> mData;//数据
    private LayoutInflater mInflater;

    private Context mContext;
    /**
     * 构造函数
     * @param context
     * @param datas
     */
    public RecyclerViewAdapter(Context context,List<ListItem> datas) {
        this.mData=datas;
        this.mContext=context;
        mInflater=LayoutInflater.from(context);

    }
    /**
     * 必须实现的方法
     * @return
     */
    @Override
    public int getItemCount() {
        return mData.size();
    }

    /**
     * 创建一个ViewHolder
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=mInflater.inflate(R.layout.recyclerview_item,parent,false);//得到item的布局
        MyViewHolder viewHolder=new MyViewHolder(view);
        return  viewHolder;
    }
    /**
     * 绑定数据ViewHolder里面的数据
     * @param holder
     * @param position
     */

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.item_recyclerview.setText(mData.get(position).getTitle());
        Picasso.with(mContext).load(mData.get(position).getPictureList()[0]).into(holder.image);
    }

}

class MyViewHolder extends RecyclerView.ViewHolder{

    TextView item_recyclerview;
    ImageView image;
    public MyViewHolder(View itemView) {
        super(itemView);
        item_recyclerview= (TextView) itemView.findViewById(R.id.item_recyclerview);
        image= (ImageView) itemView.findViewById(R.id.image);
    }
}

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
 * Created by baochen on 2016/1/13.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<MyViewHolder>{
    protected List<String> mData;//数据
    private LayoutInflater mInflater;

    /**
     * 点击事件的接口
     */
    public interface  OnItemClickListener{
        void onItemClickListener(View view, int position);//点击
        void onItemLongClickListener(View view, int position);//长按
    }
    private OnItemClickListener mOnItemClickListener;

    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    /**
     * 构造函数
     * @param context
     * @param datas
     */
    public RecyclerViewAdapter(Context context,List<String> datas) {
        this.mData=datas;
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
        holder.item_recyclerview.setText(mData.get(position));
        setUpItemEvent(holder);
    }

    /**
     * 实现瀑布流布局中的相关事件
     * @param holder
     */
    protected void setUpItemEvent(final MyViewHolder holder) {
        if (mOnItemClickListener!=null){
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               int layoutPosition=holder.getLayoutPosition();
                    mOnItemClickListener.onItemClickListener(holder.itemView,layoutPosition);
                }

        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int layoutPosition=holder.getLayoutPosition();
                mOnItemClickListener.onItemLongClickListener(holder.itemView,layoutPosition);
                return false;
            }
        });
        }
    }

    /**
     * 添加
     * @param position
     */
    public void add(int position){
        mData.add(position,"添加一个");
        notifyItemInserted(position);

    }

    /**
     * 删除
     * @param position
     */
    public void delete(int position){
        mData.remove("删除一个");
        notifyItemRemoved(position);
    }


}

class MyViewHolder extends RecyclerView.ViewHolder{

    TextView item_recyclerview;
    public MyViewHolder(View itemView) {
        super(itemView);
        item_recyclerview= (TextView) itemView.findViewById(R.id.item_recyclerview);
    }
}

package com.example.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.helper.NewColumnItem;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/4/29 0029.
 */
public class NavWrapperView extends LinearLayout{
    private List<LinearLayout> mLayouts;
    private Context mContext;
    private boolean isMoreItemsShow=false;
    private int showLineNums=1;
    private int mItemHeight;


    public NavWrapperView(Context context) {
        super(context);
        mContext=context;
    }

    public NavWrapperView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext=context;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int widthMS=MeasureSpec.getMode(widthMeasureSpec);
        int heightMS=MeasureSpec.getMode(heightMeasureSpec);
        int width=MeasureSpec.getSize(widthMeasureSpec);
        int height=MeasureSpec.getSize(heightMeasureSpec);

        int mHeightMeasureSpec=heightMeasureSpec;
//        if(!isMoreItemsShow){
//            mHeightMeasureSpec=heightMS+mItemHeight* mLayouts.size();
//            isMoreItemsShow=true;
//        }else{
//            mHeightMeasureSpec=heightMS+mItemHeight;
//            isMoreItemsShow=false;
//        }
        mHeightMeasureSpec=heightMS+mItemHeight* showLineNums;

        super.onMeasure(widthMeasureSpec, mHeightMeasureSpec);

        Log.i("ly","measure..."+widthMS+","+width+";"+heightMS+","+height);
        //setMeasuredDimension(width,height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
       // Log.i("ly","item_adapter_layout...");
    }

    public void init(List<NewColumnItem> columns, int lineLimit){
        int columnNum=columns.size();
        int columnIndex=0;
        mLayouts =new ArrayList<LinearLayout>() ;
        while(columnNum>0){
            LinearLayout layout=new LinearLayout(mContext);
            layout.setOrientation(LinearLayout.HORIZONTAL);
            LayoutParams param=new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
            layout.setLayoutParams(param);
            layout.setWeightSum(lineLimit);
            layout.setVisibility(GONE);

            int traversalCount=columnNum<lineLimit?columnNum:lineLimit;
            for(int i=0;i<traversalCount;i++){
                Button button=configButton(columns.get(columnIndex));
                layout.addView(button);
                columnIndex++;
            }
            mLayouts.add(layout);
            this.addView(layout);
            columnNum-=lineLimit;
        }
        mLayouts.get(0).setVisibility(VISIBLE);
        setViewHeight(NavWrapperView.this,mItemHeight);
    }
    private Button configButton(final NewColumnItem item){
        final Button button=new Button(mContext);

        mItemHeight=55*3;
        LayoutParams param = new LayoutParams(
                0,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                1.0f);
        button.setLayoutParams(param);
        button.setBackgroundColor(0xff00a0);
        button.setText(item.getName());
        button.setTextSize(12);
        button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                //requestLayout();
                if(item.isMore()){
                    //上传用户点击事件
                    if(!isMoreItemsShow){
                        isMoreItemsShow=true;
                        showLineNums=mLayouts.size();

                        for(LinearLayout layout: mLayouts){
                            layout.setVisibility(VISIBLE);
                        }
                        //setViewHeight(NavWrapperView.this,mItemHeight* mLayouts.size());
                        //NavWrapperView.this.measure(MeasureSpec.EXACTLY+1080,MeasureSpec.EXACTLY+mItemHeight* mLayouts.size());


                    }else{
                        isMoreItemsShow=false;
                        showLineNums=1;

                        for(LinearLayout layout: mLayouts){
                            layout.setVisibility(GONE);
                        }
                        mLayouts.get(0).setVisibility(VISIBLE);
                        //setViewHeight(NavWrapperView.this,mItemHeight);
                        //NavWrapperView.this.measure(MeasureSpec.EXACTLY+1080,MeasureSpec.EXACTLY+mItemHeight);
                    }
                }
               // Toast.makeText(mContext,"item->"+item.getName(),Toast.LENGTH_LONG).show();

            }
        });
        return button;
    }
    private void setViewHeight(ViewGroup view,int height){
        ViewGroup.LayoutParams param=view.getLayoutParams();
        param.height=height;
        view.setLayoutParams(param);
    }

}

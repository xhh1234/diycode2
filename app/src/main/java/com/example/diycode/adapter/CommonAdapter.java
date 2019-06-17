package com.example.diycode.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.diycode.R;

import java.util.List;

public abstract class CommonAdapter<T> extends RecyclerView.Adapter<ViewHolder> {

    public List<T> mdatas;//数据源
    public Context mContext;
    public int layoutId;//Layout的id
    private int loadMoreId= R.layout.load_more;

    private static final int ITEM_TYPE = 0;
    private static final int FOOTER_TYPE = 1;

    //上拉加载更多
    public static final int PULLUP_LOAD_MORE = 0;
    //正在加载中
    public static final int LOADING_MORE = 1;
    //没有加载更多 隐藏
    public static final int NO_LOAD_MORE = 2;

    //上拉加载更多状态-默认为0
    private int mLoadMoreStatus = PULLUP_LOAD_MORE;


    public CommonAdapter(List<T> mdatas, Context mContext, int layoutId) {
        this.mdatas = mdatas;
        this.mContext = mContext;
        this.layoutId = layoutId;
    }

    /**
     * 更新加载更多状态
     *
     * @param status
     */
    public void changeMoreStatus(int status) {
        mLoadMoreStatus = status;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHolder viewHolder;
        if (viewType==FOOTER_TYPE){
            viewHolder=ViewHolder.get(mContext,parent,loadMoreId);
        }else {
            viewHolder = ViewHolder.get(mContext, parent, layoutId);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (position+1==getItemCount()){
            switch (mLoadMoreStatus){
                case PULLUP_LOAD_MORE:
                    holder.setText(R.id.tvLoadText,"上拉加载更多...");
                    holder.setVisibility(R.id.pbLoad, View.GONE);
                    break;
                case LOADING_MORE:
                    holder.setText(R.id.tvLoadText,"正加载更多...");
                    holder.setVisibility(R.id.pbLoad, View.VISIBLE);
                    break;
                case NO_LOAD_MORE:
                    holder.setVisibility(R.id.loadLayout,View.GONE);
                    break;
            }
        }else {
            convert(holder, position, mdatas.get(position));
        }
    }

    public abstract void convert(ViewHolder holder,int position,T t);//实现数据与View的绑定

    @Override
    public int getItemViewType(int position) {
        return position+1!=getItemCount()?ITEM_TYPE:FOOTER_TYPE;
    }

    @Override
    public int getItemCount() {
        return mdatas.size()+1;
    }
}

package com.example.diycode.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.diycode.R;

import java.util.List;

public abstract class MultiItemTypeAdapter<T> extends RecyclerView.Adapter<ViewHolder> {
    private MultiItemViewSupport<T> multiItemViewSupport;
    public List<T> mdatas;//数据源
    public Context mContext;
    public int layoutId;//Layout的id

     private int loadMoreId= R.layout.load_more;

    //上拉加载更多
    public static final int PULLUP_LOAD_MORE = 0;
    //正在加载中
    public static final int LOADING_MORE = 1;
    //没有加载更多 隐藏
    public static final int NO_LOAD_MORE = 2;

    //上拉加载更多状态-默认为0
    private int mLoadMoreStatus = PULLUP_LOAD_MORE;

    //底部的加载
    private static final int FOOTER_TYPE = -1;


    public static final int ITEM_HEADER=1;//item做头
    public static final int ITEM_CONTENT=2;//item

    /**
     * 更新加载更多状态
     *
     * @param status
     */
    public void changeMoreStatus(int status) {
        mLoadMoreStatus = status;
        notifyDataSetChanged();
    }


    public MultiItemTypeAdapter(List<T> mdatas, Context mContext,MultiItemViewSupport<T> multiItemViewSupport) {
        this.multiItemViewSupport=multiItemViewSupport;
        this.mdatas=mdatas;
        this.mContext=mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHolder holder;
        if (viewType==FOOTER_TYPE){
            holder=ViewHolder.get(mContext,parent,loadMoreId);
        }else {
            layoutId=multiItemViewSupport.getLayoutId(viewType);
            holder=ViewHolder.get(mContext,parent,layoutId);
        }
        return holder;
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
                    holder.setText(R.id.tvLoadText,"END");
                    holder.setVisibility(R.id.pbLoad, View.GONE);
                    break;
            }
        }else {
            convert(holder,position,mdatas.get(position));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position+1==getItemCount()){
            return FOOTER_TYPE;
        }else{
            return multiItemViewSupport.getItemViewType(position, mdatas.get(position));
        }
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        RecyclerView.LayoutManager manager=recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager){
            GridLayoutManager gManager= (GridLayoutManager) manager;
            gManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int type=getItemViewType(position);
                    switch (type){
                        case ITEM_HEADER:
                        case FOOTER_TYPE:
                            return 2;
                        case ITEM_CONTENT:
                            return 1;
                            default:
                                return 0;
                    }
                }
            });
        }
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return mdatas.size()+1;
    }


    //绑定数据
    public abstract void convert(ViewHolder holder, int position, T t);
}

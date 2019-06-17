package com.example.diycode.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.diycode.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RefreshAdaper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int ITEM_TYPE = 0;
    private static final int FOOTER_TYPE = 1;
    //上拉加载更多
    public static final int PULLUP_LOAD_MORE = 0;
    //正在加载中
    public static final int LOADING_MORE = 1;
    //没有加载更多 隐藏
    public static final int NO_LOAD_MORE = 2;

    //上拉加载更多状态-默认为0
    private int mLoadMoreStatus = 0;

    public static final int TYPE_NEWS=1;
    public  int TYPE_DATA=0;

    List<String> mDatas;
    Context mContext;
    LayoutInflater mLayoutInflater;


    public RefreshAdaper(List<String> mDatas, Context mContext) {
        this.mDatas = mDatas;
        this.mContext = mContext;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    public void addRefreshData(List<String> datas) {
        mDatas.addAll(0, datas);
        notifyDataSetChanged();
    }

    public void addLoadMoreData(List<String> datas) {
        mDatas.addAll(datas);
        notifyDataSetChanged();
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
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE) {
            if (TYPE_DATA==TYPE_NEWS) {
                View itemView = mLayoutInflater.inflate(R.layout.item_news, parent, false);
                return new ItemNewsViewHolder(itemView);
            }
        } else if (viewType == FOOTER_TYPE) {
            View itemView = mLayoutInflater.inflate(R.layout.load_more, parent, false);
            return new FooterViewHolder(itemView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof ItemNewsViewHolder) {

            ItemNewsViewHolder newsViewHolder = (ItemNewsViewHolder) holder;
            newsViewHolder.tvContent.setText(mDatas.get(position).toString());
        }else if (holder instanceof FooterViewHolder){
            FooterViewHolder footerViewHolder = (FooterViewHolder) holder;
            switch (mLoadMoreStatus) {
                case PULLUP_LOAD_MORE:
                    footerViewHolder.tvLoadText.setText("上拉加载更多...");
                    break;
                case LOADING_MORE:
                    footerViewHolder.tvLoadText.setText("正加载更多...");
                    break;
                case NO_LOAD_MORE:
                    //隐藏加载更多
                    footerViewHolder.loadLayout.setVisibility(View.GONE);
                    break;
            }
        }

    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {
            //最后一个item设置为footerView
            return FOOTER_TYPE;
        } else {
            return ITEM_TYPE;
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size() + 1;
    }



    class ItemNewsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_content)
        TextView tvContent;

        public ItemNewsViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class FooterViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.pbLoad)
        ProgressBar pbLoad;
        @BindView(R.id.tvLoadText)
        TextView tvLoadText;
        @BindView(R.id.loadLayout)
        LinearLayout loadLayout;

        public FooterViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }


}

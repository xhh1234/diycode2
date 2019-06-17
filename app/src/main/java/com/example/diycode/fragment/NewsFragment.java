package com.example.diycode.fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.diycode.R;
import com.example.diycode.adapter.CommonAdapter;
import com.example.diycode.adapter.ViewHolder;
import com.example.diycode.utils.TimeUtil;
import com.example.diycode.utils.UrlUtils;
import com.mystudy.diycode_api.base.error.ExceptionHandle;
import com.mystudy.diycode_api.news.been.New;
import com.mystudy.diycode_api.news.presenter.GetNewsListPresenter;
import com.mystudy.diycode_api.news.view.GetNewsListView;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewsFragment extends SimpleRefreshFragment<New> implements GetNewsListView {
    private GetNewsListPresenter getNewsListPresenter;
    private int offset=0;
    private int limit=10;

    @Override
    public void initdata() {
        mDatas.clear();
        getNewsListPresenter=new GetNewsListPresenter(getContext());
        getNewsListPresenter.addView(this);
        getNewsListPresenter.getNewsList(null,0,limit);
        initRecyclerView();
    }

    @SuppressLint("WrongConstant")
    @Override
    public void initRecyclerView() {
        commonAdapter=new CommonAdapter<New>(mDatas,getContext(), R.layout.item_news) {
            @Override
            public void convert(ViewHolder holder, int position, New aNew) {
                holder.setText(R.id.tv_username,aNew.getUser().getLogin());
                holder.setText(R.id.tv_node_name,aNew.getNode_name());
                holder.setText(R.id.tv_title,aNew.getTitle());
                holder.setImageView(R.id.iv_avatar,aNew.getUser().getAvatar_url());
                holder.setText(R.id.tv_time, TimeUtil.computePastTime(aNew.getUpdated_at()));
                holder.setText(R.id.tv_host_name, UrlUtils.getHostName(aNew.getAddress()));
            }
        };
        linearLayoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rlvRecyclerview.setLayoutManager(linearLayoutManager);
        rlvRecyclerview.setAdapter(commonAdapter);
    }

    @Override
    public void initRefresh() {
        srlSwiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getNewsListPresenter.getNewsList(null,0,limit);
            }
        });
    }

    @Override
    public void initLoadMore() {
        rlvRecyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            int lastVisibleItem ;
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //判断RecyclerView的状态 是空闲时，同时，是最后一个可见的ITEM时才加载
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == commonAdapter.getItemCount()) {
                    //设置正在加载更多
                    offset++;
                    commonAdapter.changeMoreStatus(commonAdapter.LOADING_MORE);
                    getNewsListPresenter.getNewsList(null,offset,limit);
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager linearLayoutManager= (LinearLayoutManager) recyclerView.getLayoutManager();
                lastVisibleItem=linearLayoutManager.findLastVisibleItemPosition();
            }
        });
    }


    @Override
    public void onSuccess(List<New> news) {
        mDatas.addAll(news);
        commonAdapter.notifyDataSetChanged();
        srlSwiperefresh.setRefreshing(false);
        if (offset>0) {
            commonAdapter.changeMoreStatus(commonAdapter.PULLUP_LOAD_MORE);
        }
    }

    @Override
    public void onFail(ExceptionHandle.ResponeThrowable e) {
        Toast.makeText(getContext(), e.message, Toast.LENGTH_SHORT).show();
        srlSwiperefresh.setRefreshing(false);
        commonAdapter.changeMoreStatus(commonAdapter.PULLUP_LOAD_MORE);
    }

    @Override
    public void OnCompleted() {

    }
}

package com.example.diycode.fragment;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.diycode.R;
import com.example.diycode.adapter.CommonAdapter;
import com.example.diycode.adapter.ViewHolder;
import com.example.diycode.utils.TimeUtil;
import com.mystudy.diycode_api.base.error.ExceptionHandle;
import com.mystudy.diycode_api.topic.been.Topic;
import com.mystudy.diycode_api.topic.presenter.GetTopicsListPresenter;
import com.mystudy.diycode_api.topic.view.GetTopicsListView;


import java.util.List;

public class TopicsFragment extends SimpleRefreshFragment<Topic> implements GetTopicsListView {
    private GetTopicsListPresenter getTopicsListPresenter;


    private int offset=0;
    private int limit=10;

    @Override
    public void initLoadMore() {
        mDatas.clear();
        getTopicsListPresenter=new GetTopicsListPresenter(getContext());
        getTopicsListPresenter.addView(this);
        getTopicsListPresenter.getTopicsList(null,null,0,limit);
        initRecyclerView();
    }

    @SuppressLint("WrongConstant")
    @Override
    public void initRecyclerView() {
        super.initRecyclerView();
        commonAdapter=new CommonAdapter<Topic>(mDatas,getContext(), R.layout.item_topics) {
            @Override
            public void convert(ViewHolder holder, int position, Topic topic) {
                holder.setText(R.id.tv_username,topic.getUser().getLogin());
                holder.setImageView(R.id.iv_avatar,topic.getUser().getAvatar_url());
                holder.setText(R.id.tv_point, TimeUtil.computePastTime(topic.getUpdated_at()));
                holder.setText(R.id.tv_title,topic.getTitle());
                holder.setText(R.id.tv_node_name,topic.getNode_name());
                holder.setText(R.id.tv_state,"评论 "+topic.getReplies_count());
                holder.setOnClickLinstance(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Toast.makeText(getContext(),"点击了",Toast.LENGTH_SHORT).show();

                    }
                },R.id.rl_item);
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
                getTopicsListPresenter.getTopicsList(null,null,0,limit);
            }
        });
    }

    @Override
    public void initdata() {
        rlvRecyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            int lastVisibleItem ;
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //判断RecyclerView的状态 是空闲时，同时，是最后一个可见的ITEM时才加载
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == commonAdapter.getItemCount()) {
                    //设置正在加载更多
                    commonAdapter.changeMoreStatus(commonAdapter.LOADING_MORE);
                    offset++;
                    getTopicsListPresenter.getTopicsList(null,null,offset,limit);
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
    public void onSuccess(List<Topic> topics) {
        mDatas.addAll(topics);
        commonAdapter.notifyDataSetChanged();
        srlSwiperefresh.setRefreshing(false);
        if (offset>0) {
            commonAdapter.changeMoreStatus(CommonAdapter.PULLUP_LOAD_MORE);
        }
    }

    @Override
    public void onFail(ExceptionHandle.ResponeThrowable e) {
        Toast.makeText(getContext(),
                "更新了 " +e.message + " 条目数据", Toast.LENGTH_SHORT).show();
        srlSwiperefresh.setRefreshing(false);
        commonAdapter.changeMoreStatus(CommonAdapter.PULLUP_LOAD_MORE);
    }

    @Override
    public void OnCompleted() {

    }
}

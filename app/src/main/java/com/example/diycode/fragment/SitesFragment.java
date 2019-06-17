package com.example.diycode.fragment;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.diycode.R;
import com.example.diycode.adapter.MultiItemTypeAdapter;
import com.example.diycode.adapter.MultiItemViewSupport;
import com.example.diycode.adapter.ViewHolder;
import com.mystudy.diycode_api.base.error.ExceptionHandle;
import com.mystudy.diycode_api.sites.been.Sites;
import com.mystudy.diycode_api.sites.presenter.GetSitesPresenter;
import com.mystudy.diycode_api.sites.view.GetSitesView;


import java.util.ArrayList;
import java.util.List;

public class SitesFragment extends SimpleRefreshFragment<Object> implements GetSitesView {
    private GetSitesPresenter getSitesPresenter;


    StaggeredGridLayoutManager gridLayoutManager;
    GridLayoutManager manager;

    @Override
    public void initLoadMore() {
        mDatas.clear();
        getSitesPresenter=new GetSitesPresenter(getContext());
        getSitesPresenter.addView(this);
        getSitesPresenter.getSite();
        initRecyclerView();
    }

    @SuppressLint("WrongConstant")
    @Override
    public void initRecyclerView() {
        super.initRecyclerView();
        multiItemTypeAdapter=new MultiItemTypeAdapter<Object>(mDatas, getContext(), new MultiItemViewSupport<Object>() {
            @Override
            public int getLayoutId(int itemViewType) {
                if (itemViewType==multiItemTypeAdapter.ITEM_HEADER){
                    return R.layout.item_sites;
                }else{
                    return R.layout.item_site;
                }
            }

            @Override
            public int getItemViewType(int position, Object o) {
                if (o instanceof Sites){
                    return multiItemTypeAdapter.ITEM_HEADER;
                } else if (o instanceof Sites.Site) {
                    return multiItemTypeAdapter.ITEM_CONTENT;
                }
                return 0;
            }
        }) {
            @Override
            public void convert(ViewHolder holder, int position, Object o) {
                if (o instanceof Sites){
                    holder.setText(R.id.tv_website_name,((Sites)o).getName());
                }else if (o instanceof Sites.Site){
                    holder.setText(R.id.tv_name,((Sites.Site)o).getName());
                    holder.setImageView(R.id.iv_icon,((Sites.Site)o).getAvatar_url());
                }
            }
        };
//        linearLayoutManager=new LinearLayoutManager(getContext(),
//                LinearLayoutManager.VERTICAL,false);
//
//        gridLayoutManager=new StaggeredGridLayoutManager(2,
//                StaggeredGridLayoutManager.VERTICAL);
        manager=new GridLayoutManager(getContext(),2);
        
        rlvRecyclerview.setLayoutManager(manager);
        rlvRecyclerview.setAdapter(multiItemTypeAdapter);
    }

    @Override
    public void initRefresh() {
        srlSwiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
              getSitesPresenter.getSite();
            }
        });
    }

    @Override
    public void initdata() {
//        rlvRecyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            int lastVisibleItem ;
//            @Override
//            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//                //判断RecyclerView的状态 是空闲时，同时，是最后一个可见的ITEM时才加载
//                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == multiItemTypeAdapter.getItemCount()) {
//                    //设置正在加载更多
//                    getSitesPresenter.getSite();
//                }
//            }
//
//            @Override
//            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                LinearLayoutManager linearLayoutManager= (LinearLayoutManager) recyclerView.getLayoutManager();
//                lastVisibleItem=linearLayoutManager.findLastVisibleItemPosition();
//            }
//        });
    }

    @Override
    public void onSuccess(List<Sites> sites) {
        List<Object> itemData=new ArrayList<>();
        for (int i = 0; i < sites.size(); i++) {
            itemData.add(sites.get(i));
            for (int j = 0; j <sites.get(i).getSites().size() ; j++) {
                itemData.add(sites.get(i).getSites().get(j
                ));
            }
        }
        mDatas.addAll(itemData);
        multiItemTypeAdapter.notifyDataSetChanged();
        srlSwiperefresh.setRefreshing(false);
        multiItemTypeAdapter.changeMoreStatus(multiItemTypeAdapter.NO_LOAD_MORE);
    }

    @Override
    public void onFail(ExceptionHandle.ResponeThrowable e) {
        Toast.makeText(getContext(),e.message,Toast.LENGTH_SHORT).show();
        srlSwiperefresh.setRefreshing(false);
        multiItemTypeAdapter.changeMoreStatus(multiItemTypeAdapter.PULLUP_LOAD_MORE);
    }

    @Override
    public void OnCompleted() {
    }
}

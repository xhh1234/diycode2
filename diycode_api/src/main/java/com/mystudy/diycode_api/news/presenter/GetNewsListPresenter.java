package com.mystudy.diycode_api.news.presenter;

import android.content.Context;

import com.mystudy.diycode_api.base.Disposable.SubscriptionManager;
import com.mystudy.diycode_api.base.error.ExceptionHandle;
import com.mystudy.diycode_api.base.model.Observer;
import com.mystudy.diycode_api.base.presenter.BasePresenter;
import com.mystudy.diycode_api.news.been.New;
import com.mystudy.diycode_api.news.model.NewsModel;
import com.mystudy.diycode_api.news.view.GetNewsListView;

import java.util.List;

import io.reactivex.annotations.Nullable;
import io.reactivex.disposables.Disposable;

/**
 * (Presenter与View交互是通过接口),里面放一个接口
 */
public class GetNewsListPresenter extends BasePresenter<GetNewsListView> {
    private NewsModel newsModel;
    public GetNewsListPresenter(Context context){
        newsModel=new NewsModel(context);
    }
    //Presenter与View交互
    public void getNewsList(@Nullable Integer node_id, @Nullable Integer offset,
                            @Nullable Integer limit){
        newsModel.getNewsList(node_id, offset, limit, new Observer<List<New>>() {
            @Override
            public void OnSuccess(List<New> news) {
                view.onSuccess(news);
            }

            @Override
            public void OnFail(ExceptionHandle.ResponeThrowable e) {
                view.onFail(e);
            }

            @Override
            public void OnCompleted() {
                view.OnCompleted();
            }

            @Override
            public void OnDisposable(Disposable d) {
                SubscriptionManager.getInstance().add(d);
            }
        });
    }

}

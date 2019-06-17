package com.mystudy.diycode_api.news.presenter;


import android.content.Context;

import com.mystudy.diycode_api.base.Disposable.SubscriptionManager;
import com.mystudy.diycode_api.base.been.Node;
import com.mystudy.diycode_api.base.error.ExceptionHandle;
import com.mystudy.diycode_api.base.model.Observer;
import com.mystudy.diycode_api.base.presenter.BasePresenter;
import com.mystudy.diycode_api.news.model.NewsModel;
import com.mystudy.diycode_api.news.view.getNewsNodesListView;

import java.util.List;

import io.reactivex.disposables.Disposable;

public class getNewsNodesListPresenter extends BasePresenter<getNewsNodesListView> {
    private NewsModel newsModel;
    public getNewsNodesListPresenter(Context context){
        newsModel=new NewsModel(context);
    }
    public void getNewsNodesList(){
        newsModel.getNewsNodesList(new Observer<List<Node>>() {
            @Override
            public void OnSuccess(List<Node> nodes) {
                view.onSuccess(nodes);
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

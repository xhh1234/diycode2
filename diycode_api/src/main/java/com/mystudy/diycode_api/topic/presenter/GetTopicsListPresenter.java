package com.mystudy.diycode_api.topic.presenter;

import android.content.Context;

import com.mystudy.diycode_api.base.Disposable.SubscriptionManager;
import com.mystudy.diycode_api.base.error.ExceptionHandle;
import com.mystudy.diycode_api.base.model.Observer;
import com.mystudy.diycode_api.base.presenter.BasePresenter;
import com.mystudy.diycode_api.topic.been.Topic;
import com.mystudy.diycode_api.topic.model.TopicModel;
import com.mystudy.diycode_api.topic.view.GetTopicsListView;

import java.util.List;

import io.reactivex.disposables.Disposable;

public class GetTopicsListPresenter extends BasePresenter<GetTopicsListView> {
    private TopicModel topicModel;

    public GetTopicsListPresenter(Context context) {
        topicModel=new TopicModel(context);
    }

    public void getTopicsList(String type,Integer node_id,int offset,int limit){
        topicModel.getTopicsList(type, node_id, offset, limit, new Observer<List<Topic>>() {
            @Override
            public void OnSuccess(List<Topic> topics) {
                view.onSuccess(topics);
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

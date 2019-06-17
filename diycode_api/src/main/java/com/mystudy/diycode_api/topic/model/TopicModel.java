package com.mystudy.diycode_api.topic.model;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mystudy.diycode_api.base.model.BaseModel;
import com.mystudy.diycode_api.base.model.Observer;
import com.mystudy.diycode_api.news.been.New;
import com.mystudy.diycode_api.topic.api.TopicService;
import com.mystudy.diycode_api.topic.been.Topic;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class TopicModel extends BaseModel<TopicService> {
    public TopicModel(Context context) {
        super(context);
    }
    /**
     * 获取 topic 列表
     *
     * @param type    类型，默认 last_actived，可选["last_actived", "recent", "no_reply", "popular", "excellent"]
     * @param node_id 如果你需要只看某个节点的，请传此参数, 如果不传 则返回全部
     * @param offset  偏移数值，默认值 0
     * @param limit   数量极限，默认值 20，值范围 1..150
     */
    public void getTopicsList(String type, @NonNull Integer node_id, @NonNull int offset, @NonNull int limit
    ,@NonNull Observer<List<Topic>> observer){
        mService.getTopicsList(type,node_id,offset,limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    /**
     * @param id
     * @param offest
     * @param limit
     */
    public void getTopicRepliesList(@NonNull int id, @NonNull int offest,@NonNull int limit){

    }
}

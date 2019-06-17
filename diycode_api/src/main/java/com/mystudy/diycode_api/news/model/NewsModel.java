package com.mystudy.diycode_api.news.model;

import android.content.Context;

import com.mystudy.diycode_api.base.been.Node;
import com.mystudy.diycode_api.base.model.BaseModel;
import com.mystudy.diycode_api.base.model.Observer;
import com.mystudy.diycode_api.news.api.NewsService;
import com.mystudy.diycode_api.news.been.New;

import java.util.List;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;
import io.reactivex.schedulers.Schedulers;

public class NewsModel extends BaseModel<NewsService> {
    public NewsModel(Context context) {
        super(context);
    }
    /**
     * 获取 news 列表
     *
     * @param node_id 如果你需要只看某个节点的，请传此参数, 如果不传 则返回全部
     * @param offset  偏移数值，默认值 0
     * @param limit   数量极限，默认值 20，值范围 1..150
     */
    public void getNewsList(@Nullable Integer node_id, @Nullable Integer offset,
                            @Nullable Integer limit,Observer<List<New>> observer) {
        mService.getNewsList(node_id, offset, limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
    /**
     * 创建一个 new (分享)
     * @param title   标题
     * @param address  地址(网址链接)
     * @param node_id  节点 id
     * @param observer 返回值
     */
    public void createNews(@NonNull Integer title,@NonNull Integer address,@NonNull Integer node_id,
                           Observer<New> observer){
        mService.createNews(title, address, node_id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }

    /**
     * 获取 news 分类列表
     *
     * @return 分类列表
     */
    public void getNewsNodesList(Observer<List<Node>> observer){
        mService.getNewsNodesList().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

}

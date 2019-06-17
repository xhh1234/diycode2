package com.mystudy.diycode_api;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mystudy.diycode_api.base.Disposable.SubscriptionManager;
import com.mystudy.diycode_api.base.been.Node;
import com.mystudy.diycode_api.base.been.OAuth;
import com.mystudy.diycode_api.base.error.ExceptionHandle;
import com.mystudy.diycode_api.log.Logger;
import com.mystudy.diycode_api.news.been.New;
import com.mystudy.diycode_api.news.presenter.GetNewsListPresenter;
import com.mystudy.diycode_api.news.presenter.getNewsNodesListPresenter;
import com.mystudy.diycode_api.news.view.GetNewsListView;
import com.mystudy.diycode_api.news.view.getNewsNodesListView;
import com.mystudy.diycode_api.test.been.Hello;
import com.mystudy.diycode_api.test.presenter.TestPresenter;
import com.mystudy.diycode_api.test.view.TestView;

import java.util.List;

public class DiycodeSdk{
    private static TestPresenter testPresenter;
    private static GetNewsListPresenter getNewsListPresenter;
    private static getNewsNodesListPresenter getNewsNodesListPresenter;

    //--- 单例 -----------------------------------------------------------------------------------

    private volatile static DiycodeSdk mDiycodeSdk;

    private DiycodeSdk() {
    }


    public static DiycodeSdk getSingleInstance() {
        if (null == mDiycodeSdk) {
            synchronized (DiycodeSdk.class) {
                if (null == mDiycodeSdk) {
                    mDiycodeSdk = new DiycodeSdk();
                }
            }
        }
        return mDiycodeSdk;
    }

    //--- 初始化 ---------------------------------------------------------------------------------

    public static DiycodeSdk init(Context context,  final String client_id,
                               final String client_secret) {
//        initLogger(context);
        Logger.i("初始化 diycode");

        OAuth.client_id = client_id;
        OAuth.client_secret = client_secret;

        initPresenter(context);

        return getSingleInstance();
    }
    public static void initPresenter(Context context){
        testPresenter=new TestPresenter(context);
        testPresenter.addView(testView);

        getNewsListPresenter=new GetNewsListPresenter(context);
        getNewsListPresenter.addView(getNewsListView);

        getNewsNodesListPresenter=new getNewsNodesListPresenter(context);
        getNewsNodesListPresenter.addView(getNewsNodesListView);

    }
    //返回news 回帖列表
    private static GetNewsListView getNewsListView=new GetNewsListView() {
        @Override
        public void onSuccess(List<New> news) {
            Logger.e(String.valueOf(news.get(0).getId()));
        }

        @Override
        public void onFail(ExceptionHandle.ResponeThrowable e) {
            Logger.e(e.message.toString());
        }

        @Override
        public void OnCompleted() {

        }
    };

    public void getNewsList(@Nullable Integer node_id, @Nullable Integer offset,
                            @Nullable Integer limit){
        getNewsListPresenter.getNewsList(node_id, offset, limit);
    }
    //测试
    private static TestView testView=new TestView() {
        @Override
        public void onSuccess(Hello hello) {
            Logger.e(hello.getName());
        }

        @Override
        public void onFail(ExceptionHandle.ResponeThrowable e) {
            Logger.e(e.message.toString());
        }

        @Override
        public void OnCompleted() {

        }
    };

    public void hello(@Nullable Integer limit){
        testPresenter.hello(limit);
    }
    //获取 news 分类列表
    private static getNewsNodesListView getNewsNodesListView=new getNewsNodesListView() {
        @Override
        public void onSuccess(List<Node> nodes) {

        }

        @Override
        public void onFail(ExceptionHandle.ResponeThrowable e) {

        }

        @Override
        public void OnCompleted() {

        }
    };

    public void getNewsNodesList(){
        getNewsNodesListPresenter.getNewsNodesList();
    }




}

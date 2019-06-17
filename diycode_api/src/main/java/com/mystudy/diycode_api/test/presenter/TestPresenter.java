package com.mystudy.diycode_api.test.presenter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mystudy.diycode_api.base.Disposable.SubscriptionManager;
import com.mystudy.diycode_api.base.error.ExceptionHandle;
import com.mystudy.diycode_api.base.model.Observer;
import com.mystudy.diycode_api.base.presenter.BasePresenter;
import com.mystudy.diycode_api.test.been.Hello;
import com.mystudy.diycode_api.test.model.TestModel;
import com.mystudy.diycode_api.test.view.TestView;

import io.reactivex.disposables.Disposable;

/**
 * (Presenter与View交互是通过接口),里面放一个接口
 */
public class TestPresenter extends BasePresenter<TestView> {
    private TestModel testModel;
    public TestPresenter(Context context){
        testModel=new TestModel(context);
    }
    //Presenter与View交互
    public void hello(@Nullable Integer limit){
        testModel.hello(limit, new Observer<Hello>() {
            @Override
            public void OnSuccess(Hello hello) {
                view.onSuccess(hello);
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

package com.mystudy.diycode_api.test.model;

import android.content.Context;

import com.mystudy.diycode_api.base.model.BaseModel;
import com.mystudy.diycode_api.base.model.Observer;
import com.mystudy.diycode_api.test.api.TestService;
import com.mystudy.diycode_api.test.been.Hello;

import androidx.annotation.Nullable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class TestModel extends BaseModel<TestService> {
    public TestModel(Context context) {
        super(context);
    }
    /**
     * 简单的 API 测试接口，需要登录验证，便于快速测试 OAuth 以及其他 API 的基本格式是否正确。
     *
     * @param limit 数量极限，值范围[0..100]
     */
    public void hello(@Nullable Integer limit,Observer<Hello> observer) {
        mService.hello(limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

}

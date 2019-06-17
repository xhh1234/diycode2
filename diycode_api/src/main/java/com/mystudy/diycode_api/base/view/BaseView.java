package com.mystudy.diycode_api.base.view;

import com.mystudy.diycode_api.base.error.ExceptionHandle;

public interface BaseView<T> {
    void onSuccess(T t);

    void onFail(ExceptionHandle.ResponeThrowable e);

    void OnCompleted();
}

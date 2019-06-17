package com.mystudy.diycode_api;

public interface DiycodeSdkReturn<T> {
    void onSuccess(T t);
    void onFailse(String msg);
}

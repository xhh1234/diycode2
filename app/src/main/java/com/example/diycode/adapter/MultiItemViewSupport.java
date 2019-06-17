package com.example.diycode.adapter;

public interface MultiItemViewSupport<T> {
    int getLayoutId(int itemViewType);//返回layout的id
    int getItemViewType(int position, T t);

}

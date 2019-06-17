package com.mystudy.diycode_api.base.presenter;

import com.mystudy.diycode_api.base.view.BaseView;

/**
 * @param <V>
 * <里面传入的参数必须是BaseView的子类或者本身>
 * 这个类的作用就是获取到当前的View
 *
 * 然后V和P的关系建立，先创建基本的P层，它的作用是绑定和解除与V的关系。
 * 代码里面有注释。看代码(基本的P层)。
 */
public class BasePresenter<V extends BaseView> {
    public V view;

    //加载View,建立连接
    public void addView(V v) {
        this.view = v;
    }

    //断开连接
    public void detattch() {
        if (view != null) {
            view = null;
        }
    }
}

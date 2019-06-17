package com.mystudy.diycode_api.base.Disposable;

import io.reactivex.disposables.Disposable;


/**
 * @param <T>
 *     订阅关系的处理：网络请求不止一个，Rxjava2订阅关系就会很多，虽然Rxjava2内部
 *     实现了自动取消订阅关系（请求完成，请求报错），但是在特定情况下需要手动关闭，
 *     在结束请求或者在应有的生命周期结束时订阅关系就要取消，比如在请求还没有结束
 *     的时候，View在还没有执行完毕就退出了，那么它肯定不会自动解绑的，这时
 *     就需要我们手动解除订阅关系，否则会产生内存泄漏。
 */
public interface SubscriptionHelper<T> {
    void add(Disposable subscription);

    void cancel(Disposable t);

    void cancelall();
}

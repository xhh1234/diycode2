package com.mystudy.diycode_api.sites.presenter;

import android.content.Context;

import com.mystudy.diycode_api.base.Disposable.SubscriptionManager;
import com.mystudy.diycode_api.base.error.ExceptionHandle;
import com.mystudy.diycode_api.base.model.Observer;
import com.mystudy.diycode_api.base.presenter.BasePresenter;
import com.mystudy.diycode_api.sites.been.Sites;
import com.mystudy.diycode_api.sites.model.SitesModel;
import com.mystudy.diycode_api.sites.view.GetSitesView;

import java.util.List;

import io.reactivex.disposables.Disposable;

public class GetSitesPresenter extends BasePresenter<GetSitesView> {
    SitesModel sitesModel;
    public GetSitesPresenter(Context context) {
        sitesModel=new SitesModel(context);
    }
    public void getSite(){
        sitesModel.getSites(new Observer<List<Sites>>() {
            @Override
            public void OnSuccess(List<Sites> sites) {
                view.onSuccess(sites);
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

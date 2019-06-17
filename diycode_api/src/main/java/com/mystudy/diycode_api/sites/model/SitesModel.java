package com.mystudy.diycode_api.sites.model;

import android.content.Context;

import com.mystudy.diycode_api.base.model.BaseModel;
import com.mystudy.diycode_api.base.model.Observer;
import com.mystudy.diycode_api.sites.api.SitesService;
import com.mystudy.diycode_api.sites.been.Sites;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SitesModel extends BaseModel<SitesService> {

    public SitesModel(Context context) {
        super(context);
    }

    /**
     * 获取 酷站 列表
     * @param observer
     * @return 列表
     */
    public void getSites(Observer<List<Sites>> observer){
        mService.getSites().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}

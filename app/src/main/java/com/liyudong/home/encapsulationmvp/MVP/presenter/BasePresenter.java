package com.liyudong.home.encapsulationmvp.MVP.presenter;

import com.liyudong.home.encapsulationmvp.MVP.view.BaseView;

/**
 * Created by Administrator on 2016/11/16.
 */

public abstract class BasePresenter<V extends BaseView> {
    protected V view;

    public abstract void startGetData();

    public abstract int getUrlId(int id);

    public abstract String getUrl(String url);

    public void attachView(V view){
        this.view = view;
    }

    public void detachView(){
        if(view != null)
            view = null;
    }
}

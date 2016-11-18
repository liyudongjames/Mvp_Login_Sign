package com.liyudong.home.encapsulationmvp.MVP.presenter.impl;

import com.liyudong.home.encapsulationmvp.MVP.callback.ModelCallBack;
import com.liyudong.home.encapsulationmvp.MVP.model.StartSignModel;
import com.liyudong.home.encapsulationmvp.MVP.model.impl.StartSignModelImpl;
import com.liyudong.home.encapsulationmvp.MVP.presenter.BasePresenter;
import com.liyudong.home.encapsulationmvp.MVP.view.SignPassWordView;

/**
 * Created by Administrator on 2016/11/17.
 */

public class SignPresenter extends BasePresenter<SignPassWordView>{

    private StartSignModel model;

    public SignPresenter(){
        this.model = new StartSignModelImpl();
    }

    @Override
    public void startGetData() {
        view.signing();
        if(view != null){
            model.startSign(password, moblie, code, new ModelCallBack<String>() {

                @Override
                public void onSuccess(String s) {
                    view.afterSign();
                    view.showView(s);
                }

                @Override
                public void onFailed() {
                    view.loadfailed();
                }
            });
        }
    }

    @Override
    public int getUrlId(int id) {
        return 0;
    }

    @Override
    public String getUrl(String url) {
        return null;
    }

    private String moblie;
    public void getMobile(String moblie){
        this.moblie = moblie;
    }

    private String password;
    public void getPassword(String password){
        this.password = password;
    }

    private String code;
    public void getCode(String code){
        this.code = code;
    }
}

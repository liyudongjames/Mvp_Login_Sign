package com.liyudong.home.encapsulationmvp.MVP.presenter.impl;

import com.liyudong.home.encapsulationmvp.MVP.callback.ModelCallBack;
import com.liyudong.home.encapsulationmvp.MVP.model.StartLoginModel;
import com.liyudong.home.encapsulationmvp.MVP.model.VertificationNumber;
import com.liyudong.home.encapsulationmvp.MVP.model.impl.StartLoginModelImpl;
import com.liyudong.home.encapsulationmvp.MVP.model.impl.VertificationNumberImpl;
import com.liyudong.home.encapsulationmvp.MVP.presenter.BasePresenter;
import com.liyudong.home.encapsulationmvp.MVP.view.BaseView;
import com.liyudong.home.encapsulationmvp.MVP.view.LoginView;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2016/11/17.
 */

public class LoginPresenter extends BasePresenter<LoginView> {

    private StartLoginModel loginModel;
    private VertificationNumber vNumber;

    public LoginPresenter(){
        loginModel = new StartLoginModelImpl();
        vNumber = new VertificationNumberImpl();
    }

    @Override
    public void startGetData() {
        if(view != null) {
            view.isVertifying();
            vNumber.startVertifyNumber(mobile, new ModelCallBack<String>() {
                @Override
                public void onSuccess(String s) {
                    if(s.equals("true")) {
                        view.numberRight();
                        view.logining();
                        loginModel.startLogin(mobile, password, new ModelCallBack<String>() {
                            @Override
                            public void onSuccess(String s) {
                                view.showView(s);
                                view.afterLogin();
                            }

                            @Override
                            public void onFailed() {
                                view.loadfailed();
                                view.afterLogin();
                            }
                        });
                    }else{
                        view.numberFailed();
                        view.afterLogin();
                    }
                }

                @Override
                public void onFailed() {
                    view.numberFailed();
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

    private String mobile;
    public void getMobile(String mobile){
        this.mobile = mobile;
    }

    private String password;
    public void getPassword(String password){
        this.password = password;
    }

}

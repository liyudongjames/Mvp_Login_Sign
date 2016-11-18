package com.liyudong.home.encapsulationmvp.MVP.presenter.impl;

import com.liyudong.home.encapsulationmvp.MVP.callback.ModelCallBack;
import com.liyudong.home.encapsulationmvp.MVP.model.GetCodeModel;
import com.liyudong.home.encapsulationmvp.MVP.model.VertificationCode;
import com.liyudong.home.encapsulationmvp.MVP.model.VertificationNumber;
import com.liyudong.home.encapsulationmvp.MVP.model.impl.GetCodeModelImpl;
import com.liyudong.home.encapsulationmvp.MVP.model.impl.VertificationCodeImpl;
import com.liyudong.home.encapsulationmvp.MVP.model.impl.VertificationNumberImpl;
import com.liyudong.home.encapsulationmvp.MVP.presenter.BasePresenter;
import com.liyudong.home.encapsulationmvp.MVP.view.SignView;

/**
 * Created by Administrator on 2016/11/17.
 */

public class CodePresenter extends BasePresenter<SignView>{
    private GetCodeModel codeModel;
    private VertificationNumber numberModel;


    public CodePresenter(){
        codeModel = new GetCodeModelImpl();
        numberModel = new VertificationNumberImpl();
    }

    @Override
    public void startGetData() {
        view.vertifyPhoneNumber();
        if(view != null){
            numberModel.startVertifyNumber(mobile, new ModelCallBack<String>() {
                @Override
                public void onSuccess(String s) {
                    if(!s.equals("true")) {
                        view.numberRight();
                        codeModel.startGetCode(mobile, new ModelCallBack<String>() {
                            @Override
                            public void onSuccess(String s) {
                                view.getCodeSuccess();
                                view.getCode(s);
                            }
                            @Override
                            public void onFailed() {
                                view.getCodeFailed();
                            }
                        });
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
    private String code;
}

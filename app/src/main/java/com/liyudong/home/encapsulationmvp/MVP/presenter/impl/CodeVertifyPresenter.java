package com.liyudong.home.encapsulationmvp.MVP.presenter.impl;

import com.liyudong.home.encapsulationmvp.MVP.callback.ModelCallBack;
import com.liyudong.home.encapsulationmvp.MVP.model.VertificationCode;
import com.liyudong.home.encapsulationmvp.MVP.model.impl.VertificationCodeImpl;
import com.liyudong.home.encapsulationmvp.MVP.presenter.BasePresenter;
import com.liyudong.home.encapsulationmvp.MVP.view.SignView;

/**
 * Created by Administrator on 2016/11/17.
 */

public class CodeVertifyPresenter extends BasePresenter<SignView>{

    private VertificationCode vCodeModel;

    public CodeVertifyPresenter(){
        vCodeModel = new VertificationCodeImpl();
    }

    @Override
    public void startGetData() {
        if(view!=null){
            view.isVertifying();
            view.startVertiryCode();
            vCodeModel.startVertifyCode(mobile, vertify_code, new ModelCallBack<String>() {
                @Override
                public void onSuccess(String s) {
                    if(s.equals("0")) {
                        view.isCodeRight(s);
                    }else{
                        view.isCodeWrong();
                    }
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

    private String mobile;
    public void getMobile(String mobile){
        this.mobile = mobile;
    }
    private String vertify_code;
    public void getVertify_code(String vertify_code){
        this.vertify_code = vertify_code;
    }

}

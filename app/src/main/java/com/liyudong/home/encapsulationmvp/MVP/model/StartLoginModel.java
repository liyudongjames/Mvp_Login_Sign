package com.liyudong.home.encapsulationmvp.MVP.model;

import com.liyudong.home.encapsulationmvp.MVP.callback.ModelCallBack;

/**
 * Created by Administrator on 2016/11/17.
 */

public interface StartLoginModel {
    void startLogin(String userName,String passWord,ModelCallBack callBack);
}

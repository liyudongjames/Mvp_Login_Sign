package com.liyudong.home.encapsulationmvp.MVP.model;

import com.liyudong.home.encapsulationmvp.MVP.callback.ModelCallBack;

/**
 * Created by Administrator on 2016/11/17.
 */

public interface VertificationCode {
    String TYPE = "signup";
    void startVertifyCode(String mobile,String verify_code,ModelCallBack callBack);
}

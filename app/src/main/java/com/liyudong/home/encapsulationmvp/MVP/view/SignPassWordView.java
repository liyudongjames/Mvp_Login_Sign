package com.liyudong.home.encapsulationmvp.MVP.view;

/**
 * Created by Administrator on 2016/11/17.
 */

public interface SignPassWordView<T> extends BaseView<T>{
    void getPhoneNumber();
    void beforeSignPassword();
    void signing();
    void afterSign();
}

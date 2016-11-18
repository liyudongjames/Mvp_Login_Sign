package com.liyudong.home.encapsulationmvp.MVP.view;

/**
 * Created by Administrator on 2016/11/17.
 */

public interface LoginView<T> extends BaseView<T>{
    void beforeLogin();
    void isVertifying();
    void numberRight();
    void numberFailed();
    void logining();
    void afterLogin();
}

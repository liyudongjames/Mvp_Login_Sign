package com.liyudong.home.encapsulationmvp.MVP.view;

/**
 * Created by Administrator on 2016/11/17.
 */

public interface SignView<T> extends BaseView<T>{
    void beforeSign();
    void vertifyPhoneNumber();
    void isVertifying();
    void numberRight();
    void numberFailed();
    void getCode(String code);
    void getCodeSuccess();
    void getCodeFailed();
    void startVertiryCode();
    void isCodeRight(T t);
    void isCodeWrong();
}

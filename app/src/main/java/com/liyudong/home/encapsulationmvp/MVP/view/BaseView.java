package com.liyudong.home.encapsulationmvp.MVP.view;

/**
 * Created by Administrator on 2016/11/16.
 */

public interface BaseView<T> {
    void showView(T t);
    void loadfailed();
}

package com.liyudong.home.encapsulationmvp.MVP.callback;

/**
 * Created by Administrator on 2016/11/17.
 */

public interface ModelCallBack<T> {
    void onSuccess(T t);
    void onFailed();
}

package com.liyudong.home.encapsulationmvp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Administrator on 2016/11/16.
 */

public abstract class BaseActivity extends AppCompatActivity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        initData();
        initView();
    }

    /**
     *
     * @return 返回这个Activity对应的目标View id
     */
    public abstract int getContentView();

    /**
     * 初始化view
     */
    public abstract void initView();

    /**
     * 初始化数据
     */
    public abstract void initData();

    public <T extends View> T NoCastfindViewById(int id){
        return (T)super.findViewById(id);
    }
}

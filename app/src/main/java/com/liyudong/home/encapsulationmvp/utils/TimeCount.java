package com.liyudong.home.encapsulationmvp.utils;

import android.os.CountDownTimer;
import android.widget.Button;

/**
 * Created by Administrator on 2016/11/17.
 */

public class TimeCount extends CountDownTimer {
    private Button checking;
    public TimeCount(Button checking, long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);//参数依次为总时长,和计时的时间间隔
        this.checking = checking;
    }
    @Override
    public void onFinish() {//计时完毕时触发
        checking.setText("重新验证");
        checking.setClickable(true);
    }
    @Override
    public void onTick(long millisUntilFinished){//计时过程显示
        checking.setClickable(false);
        checking.setText(millisUntilFinished /1000+"秒");
    }
}
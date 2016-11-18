package com.liyudong.home.encapsulationmvp.MVP.model.impl;

import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.liyudong.home.encapsulationmvp.MVP.callback.ModelCallBack;
import com.liyudong.home.encapsulationmvp.MVP.model.VertificationCode;
import com.liyudong.home.encapsulationmvp.MVP.model.VertificationNumber;
import com.liyudong.home.encapsulationmvp.MVP.response.VCodeResponse;
import com.liyudong.home.encapsulationmvp.utils.APIUtils;
import com.liyudong.home.encapsulationmvp.utils.http.FormBody;
import com.liyudong.home.encapsulationmvp.utils.http.HttpUtils;
import com.liyudong.home.encapsulationmvp.utils.http.Request;
import com.liyudong.home.encapsulationmvp.utils.http.RequestBody;

import java.util.HashMap;

/**
 * Created by Administrator on 2016/11/17.
 */

public class VertificationCodeImpl implements VertificationCode{
    @Override
    public void startVertifyCode(String mobile, String verify_code,final ModelCallBack callBack) {
        HashMap<String,String> map = new HashMap<>();
        map.put("type", VertificationCode.TYPE);
        map.put("mobile",mobile);
        map.put("verify_code",verify_code);
        RequestBody body = new FormBody.Builder()
                .add("type", VertificationCode.TYPE)
                .add("mobile",mobile)
                .add("verify_code",verify_code)
                .build();
        final Request request = new Request.Builder()
                .addHeader("X-PASSPORT-APITOKEN", APIUtils.getXPassportApitoken(map))
                .url("https://passport.4c.cn/api/v1/check_verify_code?")
                .post(body)
                .build();
        HttpUtils.getInstance().execute(request, new HttpUtils.Callback() {
            @Override
            public void onResponse(String response) {
                Log.d("fuck", response);
                VCodeResponse vCodeResponse = JSONObject.parseObject(response,VCodeResponse.class);
                callBack.onSuccess(vCodeResponse.getStatus()+"");
            }

            @Override
            public void onError() {
                callBack.onFailed();
            }
        });
    }
}

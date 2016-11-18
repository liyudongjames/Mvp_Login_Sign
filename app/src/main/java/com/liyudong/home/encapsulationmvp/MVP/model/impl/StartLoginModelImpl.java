package com.liyudong.home.encapsulationmvp.MVP.model.impl;

import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.liyudong.home.encapsulationmvp.MVP.callback.ModelCallBack;
import com.liyudong.home.encapsulationmvp.MVP.model.StartLoginModel;
import com.liyudong.home.encapsulationmvp.MVP.response.LoginResponse;
import com.liyudong.home.encapsulationmvp.utils.APIUtils;
import com.liyudong.home.encapsulationmvp.utils.http.FormBody;
import com.liyudong.home.encapsulationmvp.utils.http.HttpUtils;
import com.liyudong.home.encapsulationmvp.utils.http.Request;
import com.liyudong.home.encapsulationmvp.utils.http.RequestBody;

import java.net.HttpURLConnection;
import java.util.HashMap;

/**
 * Created by Administrator on 2016/11/17.
 */

public class StartLoginModelImpl implements StartLoginModel {

    @Override
    public void startLogin(String userName,String passWord,final ModelCallBack callBack) {
        HashMap<String,String> map = new HashMap<>();
        map.put("username",userName);
        map.put("password",passWord);
        RequestBody body = new FormBody.Builder()
                .add("username",userName)
                .add("password",passWord)
                .build();
        final Request request = new Request.Builder()
                .addHeader("X-PASSPORT-APITOKEN",APIUtils.getXPassportApitoken(map))
                .url("https://passport.4c.cn/api/v1/login?")
                .post(body)
                .build();
        HttpUtils.getInstance().execute(request, new HttpUtils.Callback() {
            @Override
            public void onResponse(String response) {
                Log.d("fuck", response);
                LoginResponse loginResponse = JSONObject.parseObject(response,LoginResponse.class);
                callBack.onSuccess(loginResponse.getStatus()+loginResponse.getMessage());
            }

            @Override
            public void onError() {
                callBack.onFailed();
            }
        });
    }

}

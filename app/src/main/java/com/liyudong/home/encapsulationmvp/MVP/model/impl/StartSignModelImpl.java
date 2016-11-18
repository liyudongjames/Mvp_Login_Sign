package com.liyudong.home.encapsulationmvp.MVP.model.impl;

import android.util.Log;

import com.liyudong.home.encapsulationmvp.MVP.callback.ModelCallBack;
import com.liyudong.home.encapsulationmvp.MVP.model.StartSignModel;
import com.liyudong.home.encapsulationmvp.utils.APIUtils;
import com.liyudong.home.encapsulationmvp.utils.http.FormBody;
import com.liyudong.home.encapsulationmvp.utils.http.HttpUtils;
import com.liyudong.home.encapsulationmvp.utils.http.Request;
import com.liyudong.home.encapsulationmvp.utils.http.RequestBody;

import java.util.HashMap;

/**
 * Created by Administrator on 2016/11/17.
 */

public class StartSignModelImpl implements StartSignModel{
    @Override
    public void startSign(String password, String mobile, String verify_code,final ModelCallBack callBack) {
        HashMap<String,String> map = new HashMap<>();
        map.put("password",password);
        map.put("mobile",mobile);
        map.put("verify_code",verify_code);
        RequestBody body = new FormBody.Builder()
                .add("password",password)
                .add("mobile",mobile)
                .add("verify_code",verify_code)
                .build();
        final Request request = new Request.Builder()
                .addHeader("X-PASSPORT-APITOKEN", APIUtils.getXPassportApitoken(map))
                .url("https://passport.4c.cn/api/v1//signup?")
                .post(body)
                .build();
        HttpUtils.getInstance().execute(request, new HttpUtils.Callback() {
            @Override
            public void onResponse(String response) {
                Log.d("fuck", response);
                callBack.onSuccess(response);
            }

            @Override
            public void onError() {
                callBack.onFailed();
            }
        });
    }
}

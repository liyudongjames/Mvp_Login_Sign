package com.liyudong.home.encapsulationmvp.MVP.model.impl;

import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.liyudong.home.encapsulationmvp.MVP.callback.ModelCallBack;
import com.liyudong.home.encapsulationmvp.MVP.model.VertificationCode;
import com.liyudong.home.encapsulationmvp.MVP.model.VertificationNumber;
import com.liyudong.home.encapsulationmvp.MVP.response.VNumberResponse;
import com.liyudong.home.encapsulationmvp.utils.APIUtils;
import com.liyudong.home.encapsulationmvp.utils.http.FormBody;
import com.liyudong.home.encapsulationmvp.utils.http.HttpUtils;
import com.liyudong.home.encapsulationmvp.utils.http.Request;
import com.liyudong.home.encapsulationmvp.utils.http.RequestBody;

import java.util.HashMap;

/**
 * Created by Administrator on 2016/11/17.
 */

public class VertificationNumberImpl implements VertificationNumber {
    @Override
    public void startVertifyNumber(String mobile,final ModelCallBack callBack) {
        HashMap<String,String> map = new HashMap<>();
        map.put("field",VertificationNumber.FIELD);
        map.put("value",mobile);
        RequestBody body = new FormBody.Builder()
                .add("field",VertificationNumber.FIELD)
                .add("value",mobile)
                .build();
        final Request request = new Request.Builder()
                .addHeader("X-PASSPORT-APITOKEN", APIUtils.getXPassportApitoken(map))
                .url("https://passport.4c.cn/api/v1/exists?")
                .post(body)
                .build();
        HttpUtils.getInstance().execute(request, new HttpUtils.Callback() {
            @Override
            public void onResponse(String response) {
                Log.d("fuck", response);
                VNumberResponse vNumberResponse = JSONObject.parseObject(response,VNumberResponse.class);
                callBack.onSuccess(vNumberResponse.isExists()+"");
            }

            @Override
            public void onError() {
                callBack.onFailed();
            }
        });
    }
}

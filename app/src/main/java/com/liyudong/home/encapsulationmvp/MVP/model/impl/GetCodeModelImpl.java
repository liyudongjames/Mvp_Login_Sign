package com.liyudong.home.encapsulationmvp.MVP.model.impl;

import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.liyudong.home.encapsulationmvp.MVP.callback.ModelCallBack;
import com.liyudong.home.encapsulationmvp.MVP.model.GetCodeModel;
import com.liyudong.home.encapsulationmvp.MVP.response.GetCodeResponse;
import com.liyudong.home.encapsulationmvp.utils.APIUtils;
import com.liyudong.home.encapsulationmvp.utils.http.FormBody;
import com.liyudong.home.encapsulationmvp.utils.http.HttpUtils;
import com.liyudong.home.encapsulationmvp.utils.http.Request;
import com.liyudong.home.encapsulationmvp.utils.http.RequestBody;

import java.util.HashMap;

/**
 * Created by Administrator on 2016/11/17.
 */

public class GetCodeModelImpl implements GetCodeModel{
    @Override
    public void startGetCode(String mobile,final ModelCallBack callBack) {
        HashMap<String,String> map = new HashMap<>();
        map.put("type",GetCodeModel.TYPE);
        map.put("mobile",mobile);
        RequestBody body = new FormBody.Builder()
                .add("type",GetCodeModel.TYPE)
                .add("mobile",mobile)
                .build();
        final Request request = new Request.Builder()
                .addHeader("X-PASSPORT-APITOKEN", APIUtils.getXPassportApitoken(map))
                .url("https://passport.4c.cn/api/v1/sms?")
                .post(body)
                .build();
        HttpUtils.getInstance().execute(request, new HttpUtils.Callback() {
            @Override
            public void onResponse(String response) {
                Log.d("fuck", response);
                GetCodeResponse codeResponse = JSONObject.parseObject(response, GetCodeResponse.class);
                callBack.onSuccess(codeResponse.getStatus()+"");
            }

            @Override
            public void onError() {
                callBack.onFailed();
            }
        });
    }
}

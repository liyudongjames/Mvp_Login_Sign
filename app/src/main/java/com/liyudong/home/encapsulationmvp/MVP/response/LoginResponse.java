package com.liyudong.home.encapsulationmvp.MVP.response;

/**
 * Created by Administrator on 2016/11/17.
 */

public class LoginResponse {

    /**
     * status : 1
     * message : 用户名不存在
     */

    private int status;
    private String message;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

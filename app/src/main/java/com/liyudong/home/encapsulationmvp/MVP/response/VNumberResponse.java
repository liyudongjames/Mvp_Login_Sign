package com.liyudong.home.encapsulationmvp.MVP.response;

/**
 * Created by Administrator on 2016/11/17.
 */

public class VNumberResponse {

    /**
     * status : 0
     * exists : false
     * message : 手机号不存在
     */

    private int status;
    private boolean exists;
    private String message;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isExists() {
        return exists;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

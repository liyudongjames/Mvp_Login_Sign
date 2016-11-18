package com.liyudong.home.encapsulationmvp.MVP.response;

/**
 * Created by Administrator on 2016/11/17.
 */

public class GetCodeResponse {

    /**
     * status : 0
     * message : 发送成功
     * timeleft : 60
     */

    private int status;
    private String message;
    private int timeleft;

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

    public int getTimeleft() {
        return timeleft;
    }

    public void setTimeleft(int timeleft) {
        this.timeleft = timeleft;
    }
}

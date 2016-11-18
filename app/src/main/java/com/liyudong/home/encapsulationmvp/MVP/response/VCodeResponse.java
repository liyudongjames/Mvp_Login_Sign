package com.liyudong.home.encapsulationmvp.MVP.response;

/**
 * Created by Administrator on 2016/11/17.
 */

public class VCodeResponse {

    /**
     * status : 0
     * correct : false
     * message : 验证码不正确
     */

    private int status;
    private boolean correct;
    private String message;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

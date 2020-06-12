package edu.nju.se.yrd.iotconnmgmt.vo;

public class BasicResponse {
    protected boolean success;
    protected String message;

    public BasicResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BasicResponse message(String message) {
        this.message = message;
        return this;
    }

    public static BasicResponse ok() {
        return new BasicResponse(true, "");
    }

    public static BasicResponse error() {
        return new BasicResponse(false, "");
    }
}

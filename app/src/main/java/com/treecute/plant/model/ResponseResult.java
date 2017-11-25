package com.treecute.plant.model;

/**
 * Created by mkind on 2017/11/24 0024.
 */

public class ResponseResult<T> {

    private boolean success;
    private String message;
    private T data;
    private String errorcode;
    public boolean isSuccess() {
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
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
    public String getErrorcode() {
        return errorcode;
    }
    public void setErrorcode(String errorcode) {
        this.errorcode = errorcode;
    }
    public ResponseResult() {
    }
}

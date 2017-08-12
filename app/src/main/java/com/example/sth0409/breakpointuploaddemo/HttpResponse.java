package com.example.sth0409.breakpointuploaddemo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by suntinghui on 2017/8/12 0012.
 */

public class HttpResponse<T> {
    @SerializedName("Code")
    private String code;
    @SerializedName("msg")
    private String msg;
    @SerializedName("Result")
    private T result;
    public String getCode() {
        return code;
    }

    public boolean isSuccess() {
        if("0".equals(code)){
            return true;
        }else {
            return false;
        }
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getError() {
        return msg;
    }

    public void setError(String error) {
        this.msg = error;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "httpResponse{" +
                "code=" + code +
                ", error='" + msg + '\'' +
                ", result=" + result +
                '}';
    }
}

package com.im.moki.common.util;

import java.io.Serializable;
import java.util.Date;

public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private boolean success;
    private int errorCode;
    private String sysCode;
    private String errorMsg;
    private T model;
    private Date successTime;

    public static <T> Result<T> createSuccRes(T model) {
        Result<T> result = new Result(true);
        result.setModel(model);
        return result;
    }

    public static <T> Result<T> createSuccRes(T model, Date successTime) {
        Result<T> result = new Result(true);
        result.setModel(model);
        result.setSuccessTime(successTime);
        return result;
    }

    public static <T> Result<T> createFailedRes(int errorCode, String errorMsg) {
        Result<T> result = new Result(false);
        result.setErrorCode(errorCode);
        result.setErrorMsg(errorMsg);
        return result;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public Result(boolean success) {
        this.success = success;
    }

    public Result() {
    }

    public String getErrorMsg() {
        return this.errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public T getModel() {
        return this.model;
    }

    public void setModel(T model) {
        this.model = model;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public Date getSuccessTime() {
        return this.successTime;
    }

    public void setSuccessTime(Date successTime) {
        this.successTime = successTime;
    }

    public String getSysCode() {
        return this.sysCode;
    }

    public void setSysCode(String sysCode) {
        this.sysCode = sysCode;
    }
}


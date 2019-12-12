package com.im.moki.tomoko.constants;

public enum TomokoCode {

    DEFAULT_FAIL(9999, "系统异常！");

    private int code;
    private String msg;

    TomokoCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}

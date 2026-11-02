package com.tang.missu.core;

public class UnifyResponse {
    private int code;
    private String message;
    private String url;

    /* 因为上面的成员变量都是private的，外部无法读取，需要getter */
    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getUrl() {
        return url;
    }

    public UnifyResponse(int code, String message, String url) {
        this.code = code;
        this.message = message;
        this.url = url;
    }
}

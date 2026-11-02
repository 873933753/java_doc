package com.tang.missu.exception.http;

/* 要继承异常类 RuntimeException-运行时异常 */
public class HttpException extends RuntimeException {
    public Integer getCode() {
        return code;
    }

    public Integer getHttpStatusCode() {
        return httpStatusCode;
    }

    protected Integer code;
    protected Integer httpStatusCode = 500;
}
